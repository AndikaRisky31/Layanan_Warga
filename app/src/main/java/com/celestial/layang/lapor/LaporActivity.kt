package com.celestial.layang.lapor

import android.Manifest
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
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.celestial.layang.R
import com.celestial.layang.databinding.ActivityLaporBinding
import com.celestial.layang.home.MenuActivity
import com.celestial.layang.model.LaporanData
import com.celestial.layang.model.UserData
import com.celestial.layang.repository.LaporRepository
import com.celestial.layang.repository.UserDataRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import java.io.ByteArrayOutputStream

class LaporActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLaporBinding
    private lateinit var viewModel: LaporViewModel
    private lateinit var laporanData: LaporanData
    private lateinit var viewModelFactory: LaporViewModelFactory

    companion object {
        private const val GALLERY_REQUEST_CODE = 1
        private const val GALLERY_PERMISSION_REQUEST_CODE = 2
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLaporBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val laporRepository: LaporRepository by lazy {
            LaporRepository(this)
        }
        viewModelFactory = LaporViewModelFactory(laporRepository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(LaporViewModel::class.java)

        binding.buttonBack.setOnClickListener {
            intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)
        }
        binding.buttonKirim.setOnClickListener {
//            viewModel.saveLaporan(laporanData) //untuk mengirim data ke viewmodel yang berisi pengiriman ke api

            val dialog = Dialog(this)
            dialog.setContentView(R.layout.activity_pop_up_screen)
            dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

            val buttonOkay = dialog.findViewById<Button>(R.id.okay)
            buttonOkay.setOnClickListener {
                intent = Intent(this, MenuActivity::class.java)
                startActivity(intent)
                dialog.dismiss()
            }

            dialog.show()
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
    }

    private fun openGallery() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(intent, GALLERY_REQUEST_CODE)
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
                // Izin ditolak, berikan penanganan sesuai kebutuhan aplikasi Anda
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == RESULT_OK && data != null) {
            val selectedImageUri: Uri? = data.data
            if (selectedImageUri != null) {
                binding.ivLapor.setImageURI(selectedImageUri)
                val bitmap =
                    MediaStore.Images.Media.getBitmap(this.contentResolver, selectedImageUri)
                val base64Image = convertToBase64(bitmap)
//                laporanData = createLaporanData(base64Image) //pada bagian ini yang buat crash karena memerlukan localhostnya kayaknya
                updateButtonText()
            } else {
                // Penanganan kasus ketika gambar tidak dipilih
            }
        }
    }

    private fun createLaporanData(base64Image: String): LaporanData {
        return LaporanData(
            laporan_id = 1,
            user_id = getUserIDFromUserDataRepository(),
            bukti = base64Image,
            lokasi = binding.etLokasi.text.toString(),
            jenis = binding.etJenis.text.toString(),
            deskripsi = binding.etDesc.text.toString()
        )
    }

    private fun convertToBase64(bitmap:Bitmap): String {
        val outputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
        val byteArray = outputStream.toByteArray()
        return Base64.encodeToString(byteArray, Base64.DEFAULT)
    }

    private fun getUserIDFromUserDataRepository(): Int {
        val userDataRepository = UserDataRepository(this)
        var userId = 0
        runBlocking {
            val userData: UserData = withContext(Dispatchers.IO){
                userDataRepository.userData()
            }
            userId = userData.user_id ?: 0
        }
        return userId
    }

    private fun updateButtonText() {
        binding.btnGanti.text = "Ganti"
    }
}