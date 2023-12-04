package com.celestial.layang.layanan.riwayat

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.recyclerview.widget.LinearLayoutManager
import com.celestial.layang.R
import com.celestial.layang.databinding.FragmentRiwayatBinding
import com.celestial.layang.layanan.LayananFragment
import com.celestial.layang.layanan.ModelDanAdapter.RiwayatAdapter
import com.celestial.layang.layanan.ModelDanAdapter.RiwayatModel

class RiwayatFragment : Fragment() {
    private lateinit var _binding: FragmentRiwayatBinding
    private lateinit var adapter: RiwayatAdapter

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentRiwayatBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRiwayatAdapter()

        val fab = view.findViewById<ImageButton>(R.id.button_back)
        fab.setOnClickListener {
            val fragmentManager = requireActivity().supportFragmentManager
            val layananFragment = LayananFragment() // Ganti dengan kelas fragment "Layanan" yang sesuai
            fragmentManager.beginTransaction()
                .replace(R.id.fragment_container, layananFragment)
                .commit()
        }

    }


    private fun setRiwayatAdapter() {
        val dataList: MutableList<RiwayatModel> = mutableListOf()

        judul().forEachIndexed { index, judul ->
            dataList.add(RiwayatModel(judul, tanggal()[index], proses()[index], image()[index]))
        }
        Log.d("ISI DATANYA ", dataList.toString())
        adapter = RiwayatAdapter(dataList, this@RiwayatFragment)
        binding.rvRiwayat.layoutManager = LinearLayoutManager(requireContext())
        binding.rvRiwayat.adapter = adapter
    }

    private fun judul(): Array<String> = resources.getStringArray(R.array.judul_riwayat)
    private fun tanggal():Array<String> = resources.getStringArray(R.array.tanggal_riwayat)
    private fun proses():Array<String> = resources.getStringArray(R.array.proses)

    private fun image(): List<Int> = listOf(
        R.drawable.onproses,
        R.drawable.accept,
        R.drawable.decline,
        R.drawable.accept
    )

}