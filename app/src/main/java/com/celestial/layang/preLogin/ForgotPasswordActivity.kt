package com.celestial.layang.preLogin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.celestial.layang.databinding.ActivityForgotPasswordBinding

class ForgotPasswordActivity : AppCompatActivity() {
    private lateinit var binding : ActivityForgotPasswordBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityForgotPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //event listener kembali
        binding.linkKembali.setOnClickListener{
            intent = Intent(this,LoginActivity::class.java)
            startActivity(intent)
        }
        //event listener button kirim kode
        binding.buttonkirimkode.setOnClickListener{
            intent = Intent(this,SentForgotPasswordActivity::class.java)
            startActivity(intent)
        }
    }
}