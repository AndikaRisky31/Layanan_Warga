package com.celestial.layang.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.celestial.layang.databinding.ItemArticleBinding
import com.celestial.layang.model.ArticleModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class BeritaAdapter(private val beritaList: List<ArticleModel>?) :
    RecyclerView.Adapter<BeritaAdapter.BeritaViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BeritaViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemArticleBinding.inflate(inflater, parent, false)
        return BeritaViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BeritaViewHolder, position: Int) {
        val berita = beritaList?.get(position)
        holder.bind(berita!!)
    }

    override fun getItemCount(): Int {
        return beritaList?.size!!
    }

    inner class BeritaViewHolder(private val binding: ItemArticleBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(article: ArticleModel) {
            article.date = convertIsoDateToCustomFormat(article.date)
            binding.article = article
            Glide.with(itemView)
                .load(convertLocalhostToEmulatorIP(article.url))
                .fitCenter()
                .into(binding.imageberita)
            binding.executePendingBindings()
        }
        private fun convertLocalhostToEmulatorIP(url: String): String {
            return url.replace("http://localhost", "http://10.0.2.2")
        }
        private fun convertIsoDateToCustomFormat(isoDateString: String): String {
            val isoDateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
            val targetDateFormat = SimpleDateFormat("EEEE, dd MMMM", Locale("id", "ID"))

            try {
                val date = isoDateFormat.parse(isoDateString)
                return targetDateFormat.format(date ?: Date())
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return ""
        }

    }
}
