package com.celestial.layang.agenda

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.celestial.layang.R
import com.celestial.layang.databinding.ItemAgendaBinding
import com.celestial.layang.model.AgendaItem

class AgendaAdapter : RecyclerView.Adapter<AgendaAdapter.AgendaViewHolder>() {

    private var agendaList: List<AgendaItem> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AgendaViewHolder {
        val binding: ItemAgendaBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_agenda,
            parent,
            false
        )
        return AgendaViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AgendaViewHolder, position: Int) {
        holder.bind(agendaList[position])
    }

    override fun getItemCount(): Int {
        return agendaList.size
    }

    fun submitList(newList: List<AgendaItem>) {
        agendaList = newList
        notifyDataSetChanged()
    }

    inner class AgendaViewHolder(private val binding: ItemAgendaBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: AgendaItem) {
            binding.agendaItem = item
            Glide.with(itemView)
                .load(item.imageURL)
                .centerCrop()
                .into(binding.imageBerita)
            binding.executePendingBindings()
        }
    }
}
