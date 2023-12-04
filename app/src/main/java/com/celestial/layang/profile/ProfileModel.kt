package com.celestial.layang.profile

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.time.LocalDate

@Parcelize
data class ProfileModel(
    val id: Int,
    val Nama: String,
    val Nomor: String,
    val Email: String,
    val Alamat: String,
    val pangkat:String,
    val tempatLahir: String,
    val TanggalLahir: LocalDate
) : Parcelable
