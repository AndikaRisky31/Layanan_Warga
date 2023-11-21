package com.celestial.layang.fasilitas

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.celestial.layang.databinding.ItemFasilitasBinding

class FasilitasAdapter(private val context: Context, private val fasilitasList: List<FasilitasModel>) :
    RecyclerView.Adapter<FasilitasAdapter.FasilitasViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FasilitasViewHolder {
        val binding = ItemFasilitasBinding.inflate(
            LayoutInflater.from(context),
            parent,
            false
        )
        return FasilitasViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FasilitasViewHolder, position: Int) {
        holder.bind(fasilitasList[position])
    }

    override fun getItemCount(): Int {
        return fasilitasList.size
    }

    class FasilitasViewHolder(private val binding: ItemFasilitasBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(fasilitas: FasilitasModel) {
            binding.fasilitas = fasilitas
            binding.executePendingBindings()
        }
    }
}
