package com.celestial.layang.model

data class LaporanData(
    val laporan_id:Int,
    val user_id:Int,
    val bukti:String,
    val lokasi:String,
    val jenis:String,
    val deskripsi:String
)
