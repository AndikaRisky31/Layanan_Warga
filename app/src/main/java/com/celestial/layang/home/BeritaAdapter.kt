package com.celestial.layang.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.celestial.layang.databinding.ItemBeritaBinding

class BeritaAdapter(private val beritaList: List<BeritaModel>) :
    RecyclerView.Adapter<BeritaAdapter.BeritaViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BeritaViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemBeritaBinding.inflate(inflater, parent, false)
        return BeritaViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BeritaViewHolder, position: Int) {
        val berita = beritaList[position]
        holder.bind(berita)
    }

    override fun getItemCount(): Int {
        return beritaList.size
    }

    inner class BeritaViewHolder(private val binding: ItemBeritaBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(berita: BeritaModel) {
            binding.berita = berita
            Glide.with(itemView)
                .load(berita.imageUrl)
                .centerCrop()
                .into(binding.imageberita)
            binding.executePendingBindings()
        }
    }
}
