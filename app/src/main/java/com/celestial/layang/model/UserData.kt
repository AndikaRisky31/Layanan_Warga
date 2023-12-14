package com.celestial.layang.model

data class UserData(
    val user_id: Int,
    val kelurahan_id:Int,
    val username:String,
    val nomor:String,
    val email:String,
    val alamat:String,
    val tempatLahir: String,
    val TanggalLahir: String
)

data class AgendaItem(
    val agenda_id:Int,
    val kelurahan_id: Int,
    val judul:String,
    val imageURL:String,
    val tanggal:String,
    val tempat:String
)
