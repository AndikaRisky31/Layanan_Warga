package com.celestial.layang.notifikasi

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.time.LocalDateTime

@Parcelize
data class NotifikasiModel(
    val judul:String,
    val keterangan:String,
    val waktu:LocalDateTime
):Parcelable