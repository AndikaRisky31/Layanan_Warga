package com.celestial.layang.layanan.ModelDanAdapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.celestial.layang.databinding.PengajuanItemBinding
import com.celestial.layang.layanan.pengajuan.PengajuanFragment
import com.celestial.layang.layanan.pengajuan.PengajuanIdentityActivity

class PengajuanAdapter (private val list: List<PengajuanModel>, private val context: PengajuanFragment) :
RecyclerView.Adapter<PengajuanAdapter.ViewHolder>() {

    class ViewHolder(val binding: PengajuanItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = PengajuanItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
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
                    val intent = Intent(context, PengajuanIdentityActivity::class.java)
                    context.startActivity(intent)
                }
            }
        }
    }
}