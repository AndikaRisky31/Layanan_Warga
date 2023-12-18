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
        binding.webviewChatbot.loadUrl("https://web-chat.global.assistant.watson.appdomain.cloud/preview.html?backgroundImageURL=https%3A%2F%2Fus-south.assistant.watson.cloud.ibm.com%2Fpublic%2Fimages%2Fupx-61dc1dfb-facc-4f97-b5a7-60351d14b89d%3A%3Ad2210c3f-4b2a-4205-92cb-2a09e5fc1d1f&integrationID=22db7b3a-aa91-444a-940f-d04acb08e3db&region=us-south&serviceInstanceID=61dc1dfb-facc-4f97-b5a7-60351d14b89d")
        binding.webviewChatbot.settings.javaScriptEnabled = true
    }
}