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
        binding.webviewChatbot.loadUrl("https://cloud-object-storage-cos-standard-10m.s3.jp-tok.cloud-object-storage.appdomain.cloud/bot-assistant-mobile.html")
        binding.webviewChatbot.settings.javaScriptEnabled = true
    }
}