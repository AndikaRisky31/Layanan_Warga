package com.celestial.layang.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.celestial.layang.agenda.AgendaActivity
import com.celestial.layang.bantuan.BantuanActivity
import com.celestial.layang.databinding.FragmentBerandaBinding
import com.celestial.layang.fasilitas.FasilitasActivity
import com.celestial.layang.janjiTemu.JanjiTemuActivity
import com.celestial.layang.repository.UserPreferences
import kotlinx.coroutines.launch

class BerandaFragment : Fragment() {

    private lateinit var binding: FragmentBerandaBinding
    private lateinit var beritaAdapter: BeritaAdapter
    private lateinit var berandaViewModel: BerandaViewModel
    private lateinit var recyclerView: RecyclerView

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
            UserPreferences(requireActivity().getSharedPreferences("User_Data", Context.MODE_PRIVATE))
        }

        berandaViewModel = ViewModelProvider(this)[BerandaViewModel::class.java]

        // Observe changes in LiveData and update the UI accordingly
        berandaViewModel.beritaList.observe(viewLifecycleOwner) { latestArticles ->
            // Set up RecyclerView
            beritaAdapter = BeritaAdapter(latestArticles)
            recyclerView.adapter = beritaAdapter
        }

        binding.username.text = "Hi, ${userPreferences.getUserData().username}"

        // Set up RecyclerView using apply function
        recyclerView = binding.listberita.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        }

        // Intent for FasilitasActivity
        binding.icFasilitas.setOnClickListener {
            startActivity(Intent(requireContext(), FasilitasActivity::class.java))
        }

        binding.icJanjiTemu.setOnClickListener {
            startActivity(Intent(requireContext(), JanjiTemuActivity::class.java))
        }

        binding.icBantuan.setOnClickListener {
            startActivity(Intent(requireContext(), BantuanActivity::class.java))
        }

        binding.icAgenda.setOnClickListener {
            startActivity(Intent(requireContext(), AgendaActivity::class.java))
        }

        // Fetch latest articles when the fragment is created
        fetchLatestArticles()
    }

    private fun fetchLatestArticles() {
        lifecycleScope.launch {
            try {
                // Call the function in the ViewModel to fetch the latest articles
                berandaViewModel.fetchLatestArticles()
            } catch (e: Exception) {
                // Handle exception if an error occurs during the request
                // Display an error message or log the error
            }
        }
    }
}
