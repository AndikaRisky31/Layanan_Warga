package com.celestial.layang.janjiTemu

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.celestial.layang.databinding.ActivityProfileKontakBinding

class ProfileKontakActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProfileKontakBinding
    private lateinit var janjiTemuViewModel: JanjiTemuViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileKontakBinding.inflate(layoutInflater)
        setContentView(binding.root)
        janjiTemuViewModel = ViewModelProvider(this)[JanjiTemuViewModel::class.java]

        // Mengambil ID dari intent
        val kontakId: Int = intent.getIntExtra("kontak_id", -1)

        kontakId.let {
            val kontak: KontakModel? = janjiTemuViewModel.getKontakById(kontakId)

            kontak?.let {
                binding.apply {
                    // Bind data ke dalam layout menggunakan binding
                    this.kontak = it
                    executePendingBindings()

                    Glide.with(imageProfileKontak)
                        .load(it.imageUrl)
                        .centerCrop()
                        .into(imageProfileKontak)
                }
            }
        }
    }
}
