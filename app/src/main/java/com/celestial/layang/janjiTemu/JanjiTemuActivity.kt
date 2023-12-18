package com.celestial.layang.janjiTemu

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.celestial.layang.databinding.ActivityJanjiTemuBinding
import com.celestial.layang.model.UserData
import com.celestial.layang.repository.AdminRepository
import com.celestial.layang.repository.UserPreferences

class JanjiTemuActivity : AppCompatActivity() {

    private lateinit var binding:ActivityJanjiTemuBinding
    private lateinit var janjitemuViewModel:JanjiTemuViewModel
    private lateinit var janjiTemuAdapter: JanjiTemuAdapter
    private lateinit var repository: AdminRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityJanjiTemuBinding.inflate(layoutInflater)
        setContentView(binding.root)
        repository = AdminRepository()
        janjitemuViewModel = ViewModelProvider(this, JanjiTemuViewModelFactory(repository))[JanjiTemuViewModel::class.java]

        val recyclerView = binding.listkontak
        janjiTemuAdapter = JanjiTemuAdapter()

        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@JanjiTemuActivity)
            adapter = janjiTemuAdapter
        }
        janjitemuViewModel.kontakList.observe(this){kontakList ->
            janjiTemuAdapter.submitList(kontakList)
        }
        janjitemuViewModel.getAgendaByKelurahanId(getDataPreferences().kelurahan_id.toString())


        binding.buttonBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }

    private fun goToProfileActivity(kontak: KontakModel) {
        val intent = Intent(this, ProfileKontakActivity::class.java)
        intent.putExtra("kontak_id", kontak.id)
        startActivity(intent)
    }
    private fun getDataPreferences(): UserData {
        val userPreferences: UserPreferences by lazy {
            UserPreferences(getSharedPreferences("User_Data", MODE_PRIVATE))
        }
        return userPreferences.getUserData()
    }
}