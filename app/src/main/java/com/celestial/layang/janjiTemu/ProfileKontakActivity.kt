package com.celestial.layang.janjiTemu

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.celestial.layang.databinding.ActivityProfileKontakBinding


class ProfileKontakActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProfileKontakBinding
    private lateinit var kontakViewModel: KontakViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileKontakBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val kontakId: Int = intent.getIntExtra("id", -1)
        Log.e("id admin",kontakId.toString())

        kontakViewModel = ViewModelProvider(this)[KontakViewModel::class.java]

        kontakViewModel.getKontakById(kontakId)

        kontakViewModel.kontak.observe(this) { kontak ->
            displayKontakData(kontak)
        }
    }

    private fun displayKontakData(kontak: KontakModel) {
        binding.apply {
            // Tampilkan data KontakModel ke elemen UI
            binding.kontak = kontak
            // dll.
            // Load gambar menggunakan Glide atau cara lainnya
            Glide.with(this@ProfileKontakActivity)
                .load(kontak.imageURL)
                .centerCrop()
                .into(imageProfileKontak)
        }
    }
}

