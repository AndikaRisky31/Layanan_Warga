package com.celestial.layang.lapor

import android.Manifest
import android.app.AlertDialog
import android.app.Dialog
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Base64
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.celestial.layang.R
import com.celestial.layang.api.ApiClient
import com.celestial.layang.api.ApiService
import com.celestial.layang.databinding.ActivityLaporBinding
import com.celestial.layang.home.MenuActivity
import com.celestial.layang.model.LaporanResponse
import com.celestial.layang.repository.UserPreferences
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.ByteArrayOutputStream
import java.io.File

class LaporActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLaporBinding
    private val apiService: ApiService = ApiClient.apiService

    private val userPreferences: UserPreferences by lazy {
        UserPreferences(getSharedPreferences("User_Data", MODE_PRIVATE))
    }
    private var selectedImageUri: Uri? = null

    companion object {
        private const val GALLERY_REQUEST_CODE = 1
        private const val GALLERY_PERMISSION_REQUEST_CODE = 2
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLaporBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonBack.setOnClickListener {
            intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)
        }

        binding.btnGanti.setOnClickListener {
            if (ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.READ_EXTERNAL_STORAGE
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                    GALLERY_PERMISSION_REQUEST_CODE
                )
            } else {
                openGallery()
            }
        }

        binding.buttonKirim.setOnClickListener {
            saveLaporan()
        }
        val spinner: Spinner = binding.spinnerJenis
        ArrayAdapter.createFromResource(
            this,
            R.array.jenis_laporan_options,
            R.layout.spinner
        ).also { adapter ->
            adapter.setDropDownViewResource(R.layout.item_spinner)
            spinner.adapter = adapter
        }
    }

    private fun openGallery() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        resultLauncher.launch(intent)
    }

    private val resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                result.data?.let {
                    selectedImageUri = it.data
                    binding.ivLapor.setImageURI(selectedImageUri)
                    updateButtonText()
                }
            }
        }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == GALLERY_PERMISSION_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openGallery()
            } else {
                // Handle permission denied
            }
        }
    }


    private fun convertToBase64(uri: Uri): String {
        val bitmap = MediaStore.Images.Media.getBitmap(this.contentResolver, uri)
        val outputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
        val byteArray = outputStream.toByteArray()
        return Base64.encodeToString(byteArray, Base64.DEFAULT)
    }

    private fun updateButtonText() {
        binding.btnGanti.text = "Ganti"
    }
    private fun prepareFilePart(partName: String, base64Image: String): MultipartBody.Part {
        val decodedBytes = Base64.decode(base64Image, Base64.DEFAULT)
        val file = File(cacheDir, "temp_image.jpeg")
        file.writeBytes(decodedBytes)

        val requestFile = file.asRequestBody("multipart/form-data".toMediaTypeOrNull())
        return MultipartBody.Part.createFormData(partName, "temp_image.jpeg", requestFile)
    }

    private fun saveLaporan() {
        selectedImageUri?.let { uri ->
            val base64Image = convertToBase64(uri)

            val userId = userPreferences.getUserData().user_id.toString()
            val lokasi = binding.etLokasi.text.toString()
            val jenis = binding.spinnerJenis.selectedItem.toString()
            val deskripsi = binding.etDesc.text.toString()

            val userIdRequestBody = userId.toRequestBody("text/plain".toMediaTypeOrNull())
            val lokasiRequestBody = lokasi.toRequestBody("text/plain".toMediaTypeOrNull())
            val jenisRequestBody = jenis.toRequestBody("text/plain".toMediaTypeOrNull())
            val deskripsiRequestBody = deskripsi.toRequestBody("text/plain".toMediaTypeOrNull())
            val buktiRequestBody = prepareFilePart("bukti", base64Image)
            val call: Call<LaporanResponse> = apiService.uploadLaporan(
                userIdRequestBody,
                lokasiRequestBody,
                jenisRequestBody,
                deskripsiRequestBody,
                buktiRequestBody
            )
            call.enqueue(object : Callback<LaporanResponse> {
                override fun onResponse(call: Call<LaporanResponse>, response: Response<LaporanResponse>) {
                    if (response.isSuccessful) {
                        val yourResponse = response.body()

                        showSuccessDialog()
                    } else {
                        // Tampilkan pop-up dengan memanggil fungsi showErrorDialog() jika respons tidak sukses
                        showErrorDialog("Pengajuan failed: ${response.message()}")
                    }
                }
                override fun onFailure(call: Call<LaporanResponse>, t: Throwable) {
                    showErrorDialog("Failed to create pengajuan. Please try again.")
                }
            })


        }
    }
    private fun showSuccessDialog() {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.activity_pop_up_screen)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val buttonOkay = dialog.findViewById<Button>(R.id.okay)
        buttonOkay.setOnClickListener {
            startActivity(Intent(this, MenuActivity::class.java))
            dialog.dismiss()
        }

        dialog.show()
    }
    private fun showErrorDialog(message: String) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage(message)
        builder.setPositiveButton("OK") { dialog, which ->
            // Lakukan aksi setelah tombol OK ditekan
            // Contohnya, tutup dialog atau perintahkan pengguna untuk mencoba lagi
        }
        builder.show()
    }
}
