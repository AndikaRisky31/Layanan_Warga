package com.celestial.layang.layanan.pengajuan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import com.celestial.layang.R
import com.celestial.layang.databinding.ActivityPengajuanIdentityBinding

class PengajuanIdentityActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPengajuanIdentityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPengajuanIdentityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val buttonKembali = findViewById<ImageButton>(R.id.button_back)
        buttonKembali.setOnClickListener {
            finish()
        }

        val buttonSelanjutnya = findViewById<Button>(R.id.button_next)
        buttonSelanjutnya.setOnClickListener{
            intent = Intent(this,PengajuanBerkasActivity::class.java)
            startActivity(intent)
        }
    }
}