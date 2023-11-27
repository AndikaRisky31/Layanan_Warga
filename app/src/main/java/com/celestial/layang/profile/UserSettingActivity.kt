package com.celestial.layang.profile

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.celestial.layang.databinding.ActivityUserSettingBinding

class UserSettingActivity:AppCompatActivity() {
    private lateinit var binding:ActivityUserSettingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserSettingBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}