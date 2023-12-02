package com.celestial.layang.layanan.pengajuan

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import com.celestial.layang.R
import com.celestial.layang.home.MenuActivity

class PengajuanBerkasActivity : AppCompatActivity() {

    private lateinit var imageViewSelected: ImageView
    companion object {
        private const val GALLERY_REQUEST_CODE = 1
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pengajuan_berkas)
        imageViewSelected = findViewById(R.id.iv_ktp)
        val buttonKembali = findViewById<ImageButton>(R.id.button_back)
        buttonKembali.setOnClickListener{
            intent = Intent(this,PengajuanIdentityActivity::class.java)
            startActivity(intent)
        }

        val buttonKirim = findViewById<Button>(R.id.button_kirim)
        buttonKirim.setOnClickListener {
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

        val buttonImage = findViewById<ImageButton>(R.id.imagektp)
        buttonImage.setOnClickListener {
            // Panggil intent untuk membuka galeri
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(intent, GALLERY_REQUEST_CODE)
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == GALLERY_REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            val selectedImageUri: Uri? = data.data
            imageViewSelected.setImageURI(selectedImageUri)
        }
    }


}