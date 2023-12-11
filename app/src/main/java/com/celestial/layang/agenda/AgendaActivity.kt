package com.celestial.layang.agenda

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.celestial.layang.databinding.ActivityAgendaBinding
import com.celestial.layang.repository.AgendaRepository

class AgendaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAgendaBinding
    private lateinit var agendaAdapter: AgendaAdapter
    private lateinit var repository: AgendaRepository
    private lateinit var agendaViewModel: AgendaViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAgendaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        repository = AgendaRepository()
        agendaViewModel = ViewModelProvider(this, AgendaViewModelFactory(repository))[AgendaViewModel::class.java]

        // Inisialisasi RecyclerView dengan ID pada layout
        val recyclerView = binding.listAgenda

        // Inisialisasi adapter
        agendaAdapter = AgendaAdapter()

        // Mengatur RecyclerView dengan layout manager dan adapter
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@AgendaActivity)
            adapter = agendaAdapter
        }

        // Observasi LiveData dari ViewModel
        agendaViewModel.agendaList.observe(this) { agendaList ->
            // Pembaruan data RecyclerView ketika LiveData berubah
            agendaAdapter.submitList(agendaList)
        }

        // Panggil fungsi untuk mengambil data dari repository berdasarkan id_kelurahan
        agendaViewModel.getAgendaByKelurahanId("1")

        binding.buttonBack.setOnClickListener{
            onBackPressedDispatcher.onBackPressed()
        }
    }
}
