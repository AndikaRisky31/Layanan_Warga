package com.celestial.layang.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PengajuanData(
    var pengajuan_id: Int?,
    var user_id: Int,
    var nama: String,
    var nik: String,
    var agama: String,
    var status: String,
    var alamat: String,
    var jenis: String,
    var imageKTP: String,
    var imageKK: String
):Parcelable

