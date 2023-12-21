package com.celestial.layang.model

data class LaporanData(
    val laporan_id:Int?,
    val user_id:Int,
    val bukti_laporan:String,
    val lokasi_laporan:String,
    val jenis_laporan:String,
    val deskripsi:String
)
