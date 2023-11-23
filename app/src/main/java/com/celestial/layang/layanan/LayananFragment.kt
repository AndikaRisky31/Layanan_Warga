package com.celestial.layang.layanan

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.celestial.layang.R
import com.celestial.layang.databinding.FragmentLayananBinding
import com.celestial.layang.databinding.FragmentPengajuanBinding
import com.celestial.layang.layanan.ModelDanAdapter.LayananAdapter
import com.celestial.layang.layanan.ModelDanAdapter.LayananModel
import com.celestial.layang.layanan.ModelDanAdapter.PengajuanAdapter
import com.celestial.layang.layanan.ModelDanAdapter.PengajuanModel

class LayananFragment : Fragment() {
    private lateinit var _binding: FragmentLayananBinding
    private lateinit var adapter: LayananAdapter

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentLayananBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHomeAdapter()
    }


    private fun setHomeAdapter() {
        val dataList: MutableList<LayananModel> = mutableListOf()

        judul().forEachIndexed { index, judul ->
            dataList.add(LayananModel(judul , image()[index]))
        }
        Log.d("ISI DATANYA ", dataList.toString())
        adapter = LayananAdapter(dataList, this@LayananFragment)
        binding.rvLayanan.layoutManager = LinearLayoutManager(requireContext())
        binding.rvLayanan.adapter = adapter
    }

    private fun judul(): Array<String> = resources.getStringArray(R.array.layanan_judul)

    private fun image(): List<Int> = listOf(
        R.drawable.layanan2,
        R.drawable.layanan1,
        R.drawable.layanan3
    )

}
