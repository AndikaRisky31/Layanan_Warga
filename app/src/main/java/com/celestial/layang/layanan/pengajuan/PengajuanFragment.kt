package com.celestial.layang.layanan.pengajuan

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.recyclerview.widget.LinearLayoutManager
import com.celestial.layang.R
import com.celestial.layang.databinding.FragmentPengajuanBinding
import com.celestial.layang.layanan.LayananFragment
import com.celestial.layang.layanan.ModelDanAdapter.PengajuanAdapter
import com.celestial.layang.layanan.ModelDanAdapter.PengajuanModel


class PengajuanFragment : Fragment() {
    private lateinit var _binding: FragmentPengajuanBinding
    private lateinit var adapter: PengajuanAdapter

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentPengajuanBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHomeAdapter()
        val fab = view.findViewById<ImageButton>(R.id.button_back)
        fab.setOnClickListener {
            val fragmentManager = requireActivity().supportFragmentManager
            val layananFragment = LayananFragment() // Ganti dengan kelas fragment "Layanan" yang sesuai
            fragmentManager.beginTransaction()
                .replace(R.id.fragment_container, layananFragment)
                .commit()
        }
    }


    private fun setHomeAdapter() {
        val dataList: MutableList<PengajuanModel> = mutableListOf()

        judul().forEachIndexed { index, judul ->
            dataList.add(PengajuanModel(judul , image()[index]))
        }
        Log.d("ISI DATANYA ", dataList.toString())
        adapter = PengajuanAdapter(dataList, this)
        binding.rvPengajuan.layoutManager = LinearLayoutManager(requireContext())
        binding.rvPengajuan.adapter = adapter
    }

    private fun judul(): Array<String> = resources.getStringArray(R.array.pengajuan_judul)

    private fun image(): List<Int> = listOf(
        R.drawable.layanan2,
        R.drawable.layanan4,
        R.drawable.layanan3
    )

}