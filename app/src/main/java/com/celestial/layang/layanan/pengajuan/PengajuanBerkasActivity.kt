package com.celestial.layang.layanan.pengajuan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import com.celestial.layang.R
import com.celestial.layang.home.MenuActivity

class PengajuanBerkasActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pengajuan_berkas)
        val buttonKembali = findViewById<ImageButton>(R.id.button_back)
        buttonKembali.setOnClickListener{
            intent = Intent(this,PengajuanIdentityActivity::class.java)
            startActivity(intent)
        }

        val buttonKirim = findViewById<Button>(R.id.button_kirim)
        buttonKirim.setOnClickListener{
            intent = Intent(this,MenuActivity::class.java)
            startActivity(intent)
        }

    }


}