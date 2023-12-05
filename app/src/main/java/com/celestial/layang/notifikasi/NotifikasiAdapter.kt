package com.celestial.layang.notifikasi

// NotifikasiAdapter.kt
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.celestial.layang.databinding.ItemNotifikasiBinding

class NotifikasiAdapter(private val notifikasiList: List<NotifikasiModel>) :
    RecyclerView.Adapter<NotifikasiAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemNotifikasiBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val notifikasi = notifikasiList[position]
        holder.bind(notifikasi)
    }

    override fun getItemCount(): Int {
        return notifikasiList.size
    }

    inner class ViewHolder(private val binding: ItemNotifikasiBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(notifikasi: NotifikasiModel) {
            binding.viewmodel = notifikasi
            binding.executePendingBindings()
        }
    }
}
