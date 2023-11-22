package com.celestial.layang.layanan.persyaratan

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.celestial.layang.R
import com.celestial.layang.databinding.FragmentPersyaratanBinding
import com.celestial.layang.layanan.LayananFragment
import com.celestial.layang.layanan.ModelDanAdapter.PersyaratanAdapter
import com.celestial.layang.layanan.ModelDanAdapter.PersyaratanModel

class PersyaratanFragment : Fragment() {
    private var _binding: FragmentPersyaratanBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: PersyaratanAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPersyaratanBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setPersyaratan()

        val fab = view.findViewById<ImageButton>(R.id.button_back)
        fab.setOnClickListener {
            val fragmentManager = requireActivity().supportFragmentManager
            val layananFragment = LayananFragment() // Ganti dengan kelas fragment "Layanan" yang sesuai
            fragmentManager.beginTransaction()
                .replace(R.id.fragment_container, layananFragment)
                .commit()
        }
    }

    private fun setPersyaratan() {
        val dataList: MutableList<PersyaratanModel> = mutableListOf()

        val judulArray = resources.getStringArray(R.array.persyaratan_judul)
        val imageList = listOf(
            R.drawable.layanan2,
            R.drawable.layanan4,
            R.drawable.layanan3,
            R.drawable.layanan1
        )

        for (i in judulArray.indices) {
            val judul = judulArray[i]
            val image = imageList.getOrElse(i) { 0 }
            dataList.add(PersyaratanModel(judul, image))
        }

        adapter = PersyaratanAdapter(dataList, requireContext())
        binding.rvPersyaratan.layoutManager = LinearLayoutManager(requireContext())
        binding.rvPersyaratan.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}