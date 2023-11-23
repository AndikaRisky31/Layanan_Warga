package com.celestial.layang.janjiTemu

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.celestial.layang.databinding.ItemKontakBinding

class JanjiTemuAdapter(private val context: Context, private val kontakList: List<KontakModel>) :
    RecyclerView.Adapter<JanjiTemuAdapter.JanjiTemuViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JanjiTemuViewHolder {
        val binding = ItemKontakBinding.inflate(
            LayoutInflater.from(context),
            parent,
            false
        )
        return JanjiTemuViewHolder(binding)
    }

    override fun onBindViewHolder(holder: JanjiTemuViewHolder, position: Int) {
        holder.bind(kontakList[position])
    }

    override fun getItemCount(): Int {
        return kontakList.size
    }

    // Define your JanjiTemuViewHolder class
    class JanjiTemuViewHolder(private val binding: ItemKontakBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(kontak: KontakModel) {
            binding.kontak = kontak
            Glide.with(itemView)
                .load(kontak.imageUrl)
                .centerCrop()
                .into(binding.photoprofile)
            binding.executePendingBindings()
        }
    }
}
