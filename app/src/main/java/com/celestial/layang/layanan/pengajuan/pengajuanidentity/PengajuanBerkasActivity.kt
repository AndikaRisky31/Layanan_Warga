package com.celestial.layang.layanan.pengajuan.pengajuanidentity

import android.app.Dialog
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Base64
import android.widget.Button
import androidx.lifecycle.ViewModelProvider
import com.celestial.layang.R
import com.celestial.layang.databinding.ActivityPengajuanBerkasBinding
import com.celestial.layang.home.MenuActivity
import com.celestial.layang.model.PengajuanData
import java.io.ByteArrayOutputStream

class PengajuanBerkasActivity : AppCompatActivity() {
    private lateinit var viewModel: PengajuanIdentityViewModel
    private lateinit var pengajuanData: PengajuanData
    private lateinit var binding: ActivityPengajuanBerkasBinding

    companion object {
        private const val GALLERY_REQUEST_CODE_KTP = 1
        private const val GALLERY_REQUEST_CODE_KK = 2
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPengajuanBerkasBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(PengajuanIdentityViewModel::class.java)

        binding.buttonBack.setOnClickListener {
            intent = Intent(this, PengajuanIdentityActivity::class.java)
            startActivity(intent)
        }

        binding.buttonKirim.setOnClickListener {
            val updatedPengajuanData = createPengajuanData(pengajuanData.imageKTP, pengajuanData.imageKK)
            viewModel.savePengajuanIdentity(updatedPengajuanData)

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

        binding.imagektp.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(intent, GALLERY_REQUEST_CODE_KTP)
        }

        binding.imageKK.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(intent, GALLERY_REQUEST_CODE_KK)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == RESULT_OK && data != null) {
            val selectedImageUri: Uri? = data.data
            when (requestCode) {
                GALLERY_REQUEST_CODE_KTP -> {
                    binding.ivKtp.setImageURI(selectedImageUri)
                    val bitmap = MediaStore.Images.Media.getBitmap(this.contentResolver, selectedImageUri)
                    val base64Image = convertToBase64(bitmap)
                    pengajuanData = createPengajuanData(base64Image, pengajuanData.imageKK)
                }
                GALLERY_REQUEST_CODE_KK -> {
                    binding.ivKk.setImageURI(selectedImageUri)
                    val bitmap = MediaStore.Images.Media.getBitmap(this.contentResolver, selectedImageUri)
                    val base64Image = convertToBase64(bitmap)
                    pengajuanData = createPengajuanData(pengajuanData.imageKTP, base64Image)
                }
            }
        }
    }

    private fun createPengajuanData(imageKTP: String, imageKK: String): PengajuanData {
        return PengajuanData(
            pengajuan_id = pengajuanData.pengajuan_id, // Ubah sesuai dengan pengajuan_id yang sesuai
            user_id = pengajuanData.user_id, // Ubah sesuai dengan user_id yang sesuai
            nama = pengajuanData.nama, // Isi dengan nama yang sesuai
            nik = pengajuanData.nik,
            ttl = pengajuanData.ttl,
            agama = pengajuanData.agama,
            status = pengajuanData.status,
            alamat = pengajuanData.alamat,
            jenis = pengajuanData.jenis,
            imageKTP = imageKTP,
            imageKK = imageKK
        )
    }

    private fun convertToBase64(bitmap: Bitmap): String {
        val outputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
        return Base64.encodeToString(outputStream.toByteArray(), Base64.DEFAULT)
    }
}