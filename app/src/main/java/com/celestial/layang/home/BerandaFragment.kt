package com.celestial.layang.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.celestial.layang.agenda.AgendaActivity
import com.celestial.layang.bantuan.BantuanActivity
import com.celestial.layang.databinding.FragmentBerandaBinding
import com.celestial.layang.fasilitas.FasilitasActivity
import com.celestial.layang.janjiTemu.JanjiTemuActivity
import com.celestial.layang.repository.UserPreferences

class BerandaFragment : Fragment() {

    private lateinit var binding: FragmentBerandaBinding
    private lateinit var beritaAdapter: BeritaAdapter
    private lateinit var berandaViewModel: BerandaViewModel
    private lateinit var recyclerView:RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBerandaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val userPreferences: UserPreferences by lazy {
            UserPreferences(requireContext().getSharedPreferences("User_Data", Context.MODE_PRIVATE))
        }
        val data = userPreferences.getUserData()
        berandaViewModel = ViewModelProvider(this)[BerandaViewModel::class.java]
        binding.username.text = "Hi, ${data.username}"
        val beritaList = berandaViewModel.beritaList.value?: emptyList()

        // Inisialisasi adapter
        beritaAdapter = BeritaAdapter(beritaList)

        // Set up RecyclerView
        recyclerView = binding.listberita
        recyclerView.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
        recyclerView.adapter = beritaAdapter
        binding.executePendingBindings()
        // Intent untuk memulai FasilitasActivity
        binding.icFasilitas.setOnClickListener {
            val intent = Intent(requireContext(), FasilitasActivity::class.java)
            startActivity(intent)
        }
        binding.icJanjiTemu.setOnClickListener{
            val intent = Intent(requireContext(),JanjiTemuActivity::class.java)
            startActivity(intent)
        }
        binding.icBantuan.setOnClickListener{
            val intent = Intent(requireContext(),BantuanActivity::class.java)
            startActivity(intent)
        }
        binding.icAgenda.setOnClickListener{
            val intent = Intent(requireContext(),AgendaActivity::class.java)
            startActivity(intent)
        }

    }
}
