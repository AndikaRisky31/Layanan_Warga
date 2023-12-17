package com.celestial.layang.model


data class UserData(
    val user_id: Int,
    val kelurahan_id:Int,
    val username:String,
    val password:String,
    val nomor:String,
    val email:String,
    val alamat:String,
    val kota: String,
    val imageURL: String?
)

data class AgendaItem(
    val agenda_id:Int,
    val kelurahan_id: Int,
    val judul:String,
    val imageURL:String,
    val tanggal:String,
    val tempat:String
)
data class ArticleModel(
    val artikel_id:Int,
    val judul:String,
    val deskripsi:String,
    val tanggal:String,
    val ImageURL:String
)
