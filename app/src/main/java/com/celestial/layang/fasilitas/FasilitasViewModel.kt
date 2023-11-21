package com.celestial.layang.fasilitas

import androidx.lifecycle.ViewModel

class FasilitasViewModel :ViewModel(){
    fun getFasilitasList(): List<FasilitasModel> {
        // Gantilah dengan logika untuk mendapatkan data fasilitas dari sumber data Anda
        return listOf(
            FasilitasModel("Fasilitas 1", "Lapangan Badminton","Bengkong Sarmen kacau,Blok KK No.1-3"),
            FasilitasModel("Fasilitas 2", "POSYANDU Lansia Lestari 1","Bengkong Permai Lanjut Blok Ll No. 10"),
            FasilitasModel("Fasilitas 3", "Fasum Anak Mawar","Bengkong jatuh Asri,Blok M No. 01-12"),
            FasilitasModel("Fasilitas 4","Kelurahan Tanjung batu","Bengkong tanjung Batu,Blok AA No. 01-03"),
            FasilitasModel("Fasilitas 4","Kelurahan Tanjung batu","Bengkong tanjung Batu,Blok AA No. 01-03"),
            FasilitasModel("Fasilitas 4","Kelurahan Tanjung batu","Bengkong tanjung Batu,Blok AA No. 01-03")
        )
    }
}