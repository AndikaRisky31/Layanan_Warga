package com.celestial.layang.preLogin

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.celestial.layang.databinding.LandingscreenBinding
import com.celestial.layang.home.MenuActivity
import com.celestial.layang.model.UserData
import com.celestial.layang.register.RegisterActivity
import com.celestial.layang.repository.UserPreferences
import com.google.firebase.FirebaseApp

class LandingScreenActivity : AppCompatActivity() {

    private lateinit var binding: LandingscreenBinding
    private val userPreferences: UserPreferences by lazy {
        UserPreferences(this.getSharedPreferences("User_Data", MODE_PRIVATE))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = LandingscreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        FirebaseApp.initializeApp(this);

        // Cek apakah ada data pada UserPreferences
        if (userPreferences.isUserDataNotEmpty(userPreferences.getUserData())) {
            startActivity(Intent(this, MenuActivity::class.java))
            finish()
        }

        binding.buttonGetStarted.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }
}
