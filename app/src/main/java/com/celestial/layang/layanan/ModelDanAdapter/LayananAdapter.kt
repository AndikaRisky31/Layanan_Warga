package com.celestial.layang.layanan.ModelDanAdapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.celestial.layang.R
import com.celestial.layang.databinding.LayananItemBinding
import com.celestial.layang.layanan.LayananFragment
import com.celestial.layang.layanan.pengajuan.PengajuanFragment
import com.celestial.layang.layanan.persyaratan.PersyaratanFragment

class LayananAdapter(private val list: List<LayananModel>, private val context: LayananFragment) :
    RecyclerView.Adapter<LayananAdapter.ViewHolder>() {

    class ViewHolder(val binding: LayananItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = LayananItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            with(list[position]) {
                binding.ivImage.setImageResource(this.image)
                binding.tvJudul.text = this.judul

                holder.itemView.setOnClickListener {
                    val context = it.context
                    val judulArray = context.resources.getStringArray(R.array.layanan_judul)
                    val fragment: Fragment? = getFragmentByJudul(judulArray, this.judul)
                    if (fragment != null) {
                        val fragmentManager = (context as AppCompatActivity).supportFragmentManager
                        fragmentManager.beginTransaction()
                            .replace(R.id.fragment_container, fragment)
                            .commit()
                    }
                }
            }
        }
    }

    private fun getFragmentByJudul(judulArray: Array<String>, judul: String): Fragment? {
        val index = judulArray.indexOf(judul)
        return when (index) {
            0 -> PengajuanFragment()
            1 -> PersyaratanFragment()
            // Tambahkan case untuk indeks-indeks fragment lainnya di sini
            else -> null
        }
    }
}
