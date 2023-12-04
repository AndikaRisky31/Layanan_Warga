package com.celestial.layang.layanan.ModelDanAdapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.celestial.layang.databinding.RiwayatItemBinding
import com.celestial.layang.layanan.riwayat.RiwayatFragment

class RiwayatAdapter (private val list: List<RiwayatModel>, private val context: RiwayatFragment) :
    RecyclerView.Adapter<RiwayatAdapter.ViewHolder>() {

    class ViewHolder(val binding: RiwayatItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = RiwayatItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            with(list[position]) {
                binding.ivProses.setImageResource(this.image)
                binding.judulHistory.text = this.judul
                binding.tvTanggal.text = this.tanggal
                binding.tvProses.text = this.proses

//                holder.itemView.setOnClickListener {
//                    val context = it.context
//                    val intent = Intent(context, PengajuanIdentityActivity::class.java)
//                    context.startActivity(intent)
//                }
            }
        }
    }
}