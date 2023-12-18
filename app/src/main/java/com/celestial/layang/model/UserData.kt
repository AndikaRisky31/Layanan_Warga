package com.celestial.layang.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserData(
    var user_id: Int?,
    var kelurahan_id:Int?,
    var username:String?,
    var password:String?,
    var nomor:String?,
    var email:String?,
    var alamat:String?,
    var kota: String?,
    var imageURL: String?
):Parcelable

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
