package com.celestial.layang.layanan.ModelDanAdapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.RecyclerView
import com.celestial.layang.databinding.PengajuanItemBinding
import com.celestial.layang.layanan.pengajuan.pengajuanidentity.PengajuanIdentityActivity

class PengajuanAdapter(
    private val list: List<PengajuanModel>,
    private val context: Context
) : RecyclerView.Adapter<PengajuanAdapter.ViewHolder>() {

    class ViewHolder(val binding: PengajuanItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = PengajuanItemBinding
            .inflate(LayoutInflater
                .from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        with(holder.binding) {
            ivImage.setImageResource(item.image)
            tvJudul.text = item.judul

            holder.itemView.setOnClickListener {
                val intent = Intent(context, PengajuanIdentityActivity::class.java)
                intent.putExtra("judul", item.judul)
                context.startActivity(intent)
            }
        }
    }
}