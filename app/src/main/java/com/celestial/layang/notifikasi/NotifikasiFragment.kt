package com.celestial.layang.notifikasi

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.celestial.layang.databinding.FragmentNotifikasiBinding

class NotifikasiFragment : Fragment() {

    private lateinit var binding: FragmentNotifikasiBinding
    private lateinit var viewModel: NotifikasiViewModel
    private lateinit var notifikasiAdapter: NotifikasiAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNotifikasiBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Inisialisasi ViewModel
        viewModel = ViewModelProvider(this)[NotifikasiViewModel::class.java]
        val data = viewModel.getNotifikasiList()

        // Inisialisasi RecyclerView dan Adapter
        notifikasiAdapter = NotifikasiAdapter(data)

        binding.listNotifikasi.layoutManager = LinearLayoutManager(requireContext())
        binding.listNotifikasi.adapter = notifikasiAdapter
    }
}
