package com.celestial.layang.model

data class Daerah(
    val tes:String
)
data class DataProvinsi(
    val id : String,
    val name : String
)

data class DataKabupaten(
    val id : String,
    val province_id:String,
    val name:String
)
data class DataKecamatan(
    val id :String,
    val regency_id:String,
    val name :String
)
data class DataKelurahan(
    val id:String,
    val district_id:String,
    val name:String
)
