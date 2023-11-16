// YourActivity.kt
package com.celestial.layang.preLogin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.celestial.layang.databinding.LandingscreenBinding

class LandingScreenActivity : AppCompatActivity() {

    private lateinit var binding: LandingscreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = LandingscreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
