package com.celestial.layang.layanan.ModelDanAdapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.celestial.layang.databinding.PersyaratanItemBinding
import com.celestial.layang.layanan.persyaratan.DetailsActivity


class PersyaratanAdapter(
    private val list: List<PersyaratanModel>,
    private val context: Context
) : RecyclerView.Adapter<PersyaratanAdapter.ViewHolder>() {

    class ViewHolder(val binding: PersyaratanItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = PersyaratanItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        with(holder.binding) {
            ivImage.setImageResource(item.image)
            tvJudul.text = item.judul

            holder.itemView.setOnClickListener {
                val intent = Intent(context, DetailsActivity::class.java)
                intent.putExtra("gambar", item.image)
                intent.putExtra("judul", item.judul)
                context.startActivity(intent)
            }
        }
    }
}