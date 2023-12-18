package com.celestial.layang.janjiTemu

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class KontakModel(
    val id: Int,
    val kelurahan_id:Int,
    val imageURL: String,
    val nama: String,
    val pangkat: String,
    val nomor: String,
    val email: String,
    val alamat:String
) : Parcelable
