package com.celestial.layang.janjiTemu

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class KontakModel(
    val id: Int,
    val imageUrl: String,
    val nama: String,
    val pangkat: String,
    val nomor: String,
    val email: String,
    val alamat: String,
    val jadwal: String
) : Parcelable
