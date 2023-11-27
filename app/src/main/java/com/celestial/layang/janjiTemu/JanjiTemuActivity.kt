package com.celestial.layang.janjiTemu

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.celestial.layang.databinding.ActivityJanjiTemuBinding

class JanjiTemuActivity : AppCompatActivity() {
    private lateinit var binding:ActivityJanjiTemuBinding
    private lateinit var janjitemuadapter:JanjiTemuAdapter
    private  lateinit var janjitemuViewModel:JanjiTemuViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityJanjiTemuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        janjitemuViewModel = ViewModelProvider(this)[JanjiTemuViewModel::class.java]


        val kontakList = janjitemuViewModel.getFasilitasList()


        val recyclerView = binding.listkontak


        val adapter = JanjiTemuAdapter(this, kontakList) { kontak ->
            goToProfileActivity(kontak)
        }
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        binding.buttonBack.setOnClickListener{
            onBackPressedDispatcher.onBackPressed()
        }
    }
    private fun goToProfileActivity(kontak: KontakModel) {
        val intent = Intent(this, ProfileKontakActivity::class.java)
        intent.putExtra("kontak_id", kontak.id) // Sesuaikan dengan data yang ingin Anda lewatkan
        startActivity(intent)
    }
}