package com.celestial.layang.model

data class PengajuanData(
    val pengajuan_id: Int,
    val user_id: Int,
    val nama: String,
    val nik: Int,
    val ttl: String,
    val agama: String,
    val status: String,
    val alamat: String,
    val jenis: String,
    val imageKTP: String,
    val imageKK: String
)

