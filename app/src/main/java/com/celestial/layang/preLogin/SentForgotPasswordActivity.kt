package com.celestial.layang.preLogin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.celestial.layang.databinding.ActivitySentForgotPasswordBinding

class SentForgotPasswordActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySentForgotPasswordBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySentForgotPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //event listener button melanjutkan
        binding.buttonMelanjutkan.setOnClickListener{
            intent = Intent(this,LoginActivity::class.java)
            startActivity(intent)
        }
    }
}