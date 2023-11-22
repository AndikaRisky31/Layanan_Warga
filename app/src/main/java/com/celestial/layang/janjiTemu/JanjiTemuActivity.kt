package com.celestial.layang.janjiTemu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.celestial.layang.databinding.ActivityJanjiTemuBinding

class JanjiTemuActivity : AppCompatActivity() {
    private lateinit var binding:ActivityJanjiTemuBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityJanjiTemuBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}