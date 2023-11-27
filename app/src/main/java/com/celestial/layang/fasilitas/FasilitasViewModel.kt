package com.celestial.layang.fasilitas

import androidx.lifecycle.ViewModel

class FasilitasViewModel :ViewModel(){
    fun getFasilitasList(): List<FasilitasModel> {
        return listOf(
            FasilitasModel(
                "https://pixabay.com/get/gbdd045c7ed6ba217afa0dc4027a3885dfb3abc089860889fb271538d9ed8ed02a481dea302b9827ed99208445400dffa_1280.jpg",
                "Lapangan Badminton",
                "Bengkong Sarmen kacau,Blok KK No.1-3"),
            FasilitasModel(
                "https://picsum.photos/200",
                "POSYANDU Lansia Lestari 1",
                "Bengkong Permai Lanjut Blok Ll No. 10"),
            FasilitasModel(
                "https://picsum.photos/200",
                "Fasum Anak Mawar",
                "Bengkong jatuh Asri,Blok M No. 01-12"),
            FasilitasModel(
                "https://picsum.photos/200",
                "Kelurahan Tanjung batu",
                "Bengkong tanjung Batu,Blok AA No. 01-03")
        )
    }
}