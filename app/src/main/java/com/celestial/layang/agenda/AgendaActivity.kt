package com.celestial.layang.agenda

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.celestial.layang.databinding.ActivityAgendaBinding
import com.celestial.layang.model.UserData
import com.celestial.layang.repository.AgendaRepository
import com.celestial.layang.repository.UserPreferences

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

        val recyclerView = binding.listAgenda
        agendaAdapter = AgendaAdapter()

        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@AgendaActivity)
            adapter = agendaAdapter
        }
        agendaViewModel.agendaList.observe(this) { agendaList ->
            agendaAdapter.submitList(agendaList)
        }
        agendaViewModel.getAgendaByKelurahanId(getDataPreferences().kelurahan_id.toString())

        binding.buttonBack.setOnClickListener{
            onBackPressedDispatcher.onBackPressed()
        }
    }
    private fun getDataPreferences():UserData{
        val userPreferences: UserPreferences by lazy {
            UserPreferences(getSharedPreferences("User_Data", MODE_PRIVATE))
        }
        return userPreferences.getUserData()
    }
}
