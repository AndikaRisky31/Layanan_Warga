package com.celestial.layang.janjiTemu

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.celestial.layang.R
import com.celestial.layang.databinding.ItemKontakBinding

class JanjiTemuAdapter :
    RecyclerView.Adapter<JanjiTemuAdapter.JanjiTemuViewHolder>() {

    private var kontakList: List<KontakModel> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JanjiTemuViewHolder {
        val binding: ItemKontakBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_kontak,
            parent,
            false
        )
        return JanjiTemuViewHolder(binding)
    }

    override fun onBindViewHolder(holder: JanjiTemuViewHolder, position: Int) {
        val kontak = kontakList[position]

        // Bind data to the ViewHolder
        holder.bind(kontak)

        // Set click listener
        holder.itemView.setOnClickListener {
            // Handle item click with the KontakModel
            holder.itemView.setOnClickListener {
                showItemClickToast(holder.itemView.context, kontak)
            }
        }
    }

    override fun getItemCount(): Int {
        return kontakList.size
    }

    fun submitList(newList: List<KontakModel>) {
        kontakList = newList
        notifyDataSetChanged()
    }

    inner class JanjiTemuViewHolder(val binding: ItemKontakBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                // Handle item click with the KontakModel
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val kontak = kontakList[position]
                    showItemClickToast(binding.root.context, kontak)

                }
            }
        }

        fun bind(item: KontakModel) {
            binding.kontak = item
            Glide.with(binding.root)
                .load(item.imageURL)
                .centerCrop()
                .into(binding.photoprofile)
            binding.executePendingBindings()
        }
    }

    private fun showItemClickToast(context: Context, item: KontakModel) {
        val message = "Item clicked: ${item.nama}"
        val intent = Intent(context,ProfileKontakActivity::class.java)
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        intent.putExtra("id",item.id)
        context.startActivity(intent)
    }

}
