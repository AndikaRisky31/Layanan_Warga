package com.celestial.layang.lapor

import android.app.Dialog
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Base64
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.celestial.layang.R
import com.celestial.layang.databinding.ActivityLaporBinding
import com.celestial.layang.home.MenuActivity
import com.celestial.layang.lapor.LaporViewModel
import com.celestial.layang.model.LaporanData
import com.celestial.layang.model.UserData
import com.celestial.layang.repository.UserDataRepository
import kotlinx.coroutines.runBlocking
import java.io.ByteArrayOutputStream

class LaporActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLaporBinding
    private lateinit var viewModel: LaporViewModel
    private lateinit var laporanData: LaporanData

    companion object {
        private const val GALLERY_REQUEST_CODE = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLaporBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Inisialisasi LaporViewModel
        viewModel = ViewModelProvider(this).get(LaporViewModel::class.java)

        binding.buttonBack.setOnClickListener {
            intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)
        }
        binding.buttonKirim.setOnClickListener {
            viewModel.saveLaporan(laporanData)

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
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(intent, GALLERY_REQUEST_CODE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == RESULT_OK && data != null) {
            val selectedImageUri: Uri? = data.data
            if (selectedImageUri != null) {
                binding.ivLapor.setImageURI(selectedImageUri)
                val bitmap = MediaStore.Images.Media.getBitmap(this.contentResolver, selectedImageUri)
                val base64Image = convertToBase64(bitmap)
                laporanData = createLaporanData(base64Image)
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

    private fun convertToBase64(bitmap: Bitmap): String {
        val outputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
        return Base64.encodeToString(outputStream.toByteArray(), Base64.DEFAULT)
    }

    private fun updateButtonText() {
        val buttonImage = binding.btnGanti
        if (binding.ivLapor.drawable != null) {
            // Jika imageViewSelected memiliki gambar yang ditetapkan
            buttonImage.text = "Ganti Gambar"
        } else {
            // Jika imageViewSelected tidak memiliki gambar yang ditetapkan
            buttonImage.text = "Pilih Gambar"
        }
    }

    private fun getUserIDFromUserDataRepository(): Int {
        val userDataRepository = UserDataRepository(this)
        val userData: UserData = runBlocking {
            userDataRepository.userData()
        }
        return userData.user_id
    }
}