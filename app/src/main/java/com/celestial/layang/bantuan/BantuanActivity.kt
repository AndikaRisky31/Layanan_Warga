package com.celestial.layang.bantuan

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import com.celestial.layang.R
import com.celestial.layang.databinding.ActivityChatbotBinding

class BantuanActivity : AppCompatActivity() {
    private lateinit var binding:ActivityChatbotBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatbotBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.webviewChatbot.loadUrl("https://layang-chat.vercel.app/")
        binding.webviewChatbot.settings.apply {
            javaScriptEnabled = true
            domStorageEnabled = true
        }
    }
}