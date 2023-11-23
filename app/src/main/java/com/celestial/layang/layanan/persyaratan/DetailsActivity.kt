package com.celestial.layang.layanan.persyaratan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import com.celestial.layang.R
import com.celestial.layang.databinding.ActivityDetailsBinding

class DetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val gambar = intent.getIntExtra("gambar", 0)
        val judul = intent.getStringExtra("judul")

        binding.ivImage.setImageResource(gambar)
        binding.tvJudul.text = judul

        val buttonKembali = findViewById<ImageButton>(R.id.button_back)
        buttonKembali.setOnClickListener {
            finish()
        }
    }
}