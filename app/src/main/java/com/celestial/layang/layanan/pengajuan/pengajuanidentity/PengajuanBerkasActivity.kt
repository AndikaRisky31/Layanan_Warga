package com.celestial.layang.layanan.pengajuan.pengajuanidentity

import android.app.AlertDialog
import android.app.Dialog
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Base64
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.celestial.layang.R
import com.celestial.layang.api.ApiClient
import com.celestial.layang.api.ApiService
import com.celestial.layang.databinding.ActivityPengajuanBerkasBinding
import com.celestial.layang.home.MenuActivity
import com.celestial.layang.model.PengajuanData
import com.celestial.layang.model.PengajuanResponse
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.ByteArrayOutputStream
import java.io.File

@Suppress("DEPRECATION")
class PengajuanBerkasActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPengajuanBerkasBinding
    private lateinit var apiService: ApiService

    companion object {
        private const val GALLERY_REQUEST_CODE_KTP = 1
        private const val GALLERY_REQUEST_CODE_KK = 2
    }
    private lateinit var halamanPertamaData: PengajuanData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPengajuanBerkasBinding.inflate(layoutInflater)
        setContentView(binding.root)
        apiService = ApiClient.apiService

        // Retrieve PengajuanData from Intent
        halamanPertamaData = intent.getParcelableExtra("halamanPertamaData")!!

        binding.buttonKirim.setOnClickListener {
            kirimPengajuan()
        }

        binding.imagektp.setOnClickListener {
            startGalleryIntent(GALLERY_REQUEST_CODE_KTP)
        }

        binding.imageKK.setOnClickListener {
            startGalleryIntent(GALLERY_REQUEST_CODE_KK)
        }
    }

    private fun startGalleryIntent(requestCode: Int) {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(intent, requestCode)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == RESULT_OK && data != null) {
            val selectedImageUri: Uri? = data.data
            when (requestCode) {
                GALLERY_REQUEST_CODE_KTP -> handleGalleryResult(selectedImageUri, "imageKTP")
                GALLERY_REQUEST_CODE_KK -> handleGalleryResult(selectedImageUri, "imageKK")
            }
        }
    }

    private fun handleGalleryResult(selectedImageUri: Uri?, imageType: String) {
        selectedImageUri?.let {
            val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, it)
            val base64Image = convertToBase64(bitmap)

            // Update PengajuanData
            when (imageType) {
                "imageKTP" -> {
                    binding.ivKtp.setImageURI(it)
                    halamanPertamaData.imageKTP = base64Image
                }
                "imageKK" -> {
                    binding.ivKk.setImageURI(it)
                    halamanPertamaData.imageKK = base64Image
                }
            }
        }
    }

    private fun kirimPengajuan() {
        val userId = halamanPertamaData.user_id.toString().toRequestBody("text/plain".toMediaTypeOrNull())
        val namaLengkap = halamanPertamaData.nama.toRequestBody("text/plain".toMediaTypeOrNull())
        val noNik = halamanPertamaData.nik.toString().toRequestBody("text/plain".toMediaTypeOrNull())
        val agama = halamanPertamaData.agama.toRequestBody("text/plain".toMediaTypeOrNull())
        val status = halamanPertamaData.status.toRequestBody("text/plain".toMediaTypeOrNull())
        val alamat = halamanPertamaData.alamat.toRequestBody("text/plain".toMediaTypeOrNull())
        val jenisSurat = halamanPertamaData.jenis.toRequestBody("text/plain".toMediaTypeOrNull())
        val fileKTP = prepareFilePart("fileKTP", halamanPertamaData.imageKTP)
        val fileKK = prepareFilePart("fileKK", halamanPertamaData.imageKK)

        val call: Call<PengajuanResponse> = apiService.createPengajuan(
            userId,
            namaLengkap,
            noNik,
            agama,
            status,
            alamat,
            jenisSurat,
            fileKTP,
            fileKK
        )

        call.enqueue(object : Callback<PengajuanResponse> {
            override fun onResponse(call: Call<PengajuanResponse>, response: Response<PengajuanResponse>) {
                if (response.isSuccessful) {
                    val yourResponse = response.body()

                    // Tampilkan pop-up dengan memanggil fungsi showSuccessDialog() jika respons sukses
                    showSuccessDialog()
                } else {
                    // Tampilkan pop-up dengan memanggil fungsi showErrorDialog() jika respons tidak sukses
                    showErrorDialog("Pengajuan failed: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<PengajuanResponse>, t: Throwable) {
                // Tampilkan pop-up dengan memanggil fungsi showErrorDialog() jika terjadi kegagalan
                showErrorDialog("Failed to create pengajuan. Please try again.")
            }
        })

    }

    private fun prepareFilePart(partName: String, base64Image: String): MultipartBody.Part {
        val decodedBytes = Base64.decode(base64Image, Base64.DEFAULT)
        val file = File(cacheDir, "temp_image.jpeg")
        file.writeBytes(decodedBytes)

        val requestFile = file.asRequestBody("multipart/form-data".toMediaTypeOrNull())
        return MultipartBody.Part.createFormData(partName, "temp_image.jpeg", requestFile)
    }

    private fun convertToBase64(bitmap: Bitmap): String {
        val outputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
        return Base64.encodeToString(outputStream.toByteArray(), Base64.DEFAULT)
    }

    private fun showSuccessDialog() {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.activity_pop_up_screen)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        // Tambahkan aksi atau manipulasi UI sesuai kebutuhan di sini

        val buttonOkay = dialog.findViewById<Button>(R.id.okay)
        buttonOkay.setOnClickListener {
            // Lakukan aksi setelah tombol OK ditekan
            // Contohnya, pindah ke layar beranda atau tutup aktivitas ini
            val intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)
            finish()

            // Tutup dialog setelah melakukan aksi yang diinginkan
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
