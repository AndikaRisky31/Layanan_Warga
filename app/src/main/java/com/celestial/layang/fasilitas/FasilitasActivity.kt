package com.celestial.layang.fasilitas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.celestial.layang.databinding.ActivityFasilitasBinding

class FasilitasActivity : AppCompatActivity() {
    private lateinit var binding:ActivityFasilitasBinding
    private  lateinit var fasilitasAdapter: FasilitasAdapter
    private lateinit var fasilitasViewModel: FasilitasViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFasilitasBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // Inisialisasi ViewModel
        fasilitasViewModel = ViewModelProvider(this)[FasilitasViewModel::class.java]

        // Mengambil data dari ViewModel
        val fasilitasList = fasilitasViewModel.getFasilitasList()

        // Inisialisasi RecyclerView dengan ID pada layout
        val recyclerView = binding.listFasilitas

        // Inisialisasi adapter dan memasukkan datalist
        fasilitasAdapter = FasilitasAdapter(this,fasilitasList)

        // Menggabungkan RecyclerView dengan adapter
        recyclerView.adapter = fasilitasAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        binding.buttonBack.setOnClickListener{
            onBackPressedDispatcher.onBackPressed()
        }
    }
}