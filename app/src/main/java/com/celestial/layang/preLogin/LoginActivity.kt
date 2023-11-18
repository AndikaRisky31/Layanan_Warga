package com.celestial.layang.preLogin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.celestial.layang.databinding.ActivityLoginBinding
import com.celestial.layang.home.MenuActivity

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //event listener teks lupa password
        binding.txtlupapass.setOnClickListener{
            intent = Intent(this,ForgotPasswordActivity::class.java)
            startActivity(intent)
        }
        //event listener teks mendaftar
        binding.linkMendaftar.setOnClickListener{
            intent = Intent(this,RegisterActivity::class.java)
            startActivity(intent)
        }
        //event listener button login
        binding.buttonMasuk.setOnClickListener {
            intent = Intent(this,MenuActivity::class.java)
            startActivity(intent)
        }
    }
}