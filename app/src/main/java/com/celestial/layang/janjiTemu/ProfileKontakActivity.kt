package com.celestial.layang.janjiTemu

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.celestial.layang.databinding.ActivityProfileKontakBinding

class ProfileKontakActivity: AppCompatActivity() {
    private lateinit var binding:ActivityProfileKontakBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileKontakBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

}