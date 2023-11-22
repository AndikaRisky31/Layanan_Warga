package com.celestial.layang.janjiTemu

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.celestial.layang.databinding.ItemFasilitasBinding

class JanjiTemuAdapter(private val context: Context, private val kontakList: List<KontakModel>) :
    RecyclerView.Adapter<JanjiTemuAdapter.JanjiTemuViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JanjiTemuViewHolder {
        val binding = ItemFasilitasBinding.inflate(
            LayoutInflater.from(context),
            parent,
            false
        )
        return JanjiTemuViewHolder(binding)
    }

    override fun onBindViewHolder(holder: JanjiTemuViewHolder, position: Int) {
        // Implement your binding logic here
        // Example: holder.bind(kontakList[position])
    }

    override fun getItemCount(): Int {
        return kontakList.size
    }

    // Define your JanjiTemuViewHolder class
    class JanjiTemuViewHolder(binding: ItemFasilitasBinding) :
        RecyclerView.ViewHolder(binding.root) {
        // Implement your ViewHolder logic here
        // Example: val textView = binding.textView
    }
}
