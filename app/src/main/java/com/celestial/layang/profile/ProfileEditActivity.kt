package com.celestial.layang.profile

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.celestial.layang.databinding.ActivityProfileEditBinding

class ProfileEditActivity:AppCompatActivity() {
    private lateinit var binding:ActivityProfileEditBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileEditBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}