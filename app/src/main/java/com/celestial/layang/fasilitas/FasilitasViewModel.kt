package com.celestial.layang.fasilitas

import androidx.lifecycle.ViewModel

class FasilitasViewModel :ViewModel(){
    fun getFasilitasList(): List<FasilitasModel> {
        // Gantilah dengan logika untuk mendapatkan data fasilitas dari sumber data Anda
        return listOf(
            FasilitasModel("https://pixabay.com/get/g331ea08842c650160f75fb9a9417245d3c2f4d56db8e07dde7ee63b07f10c334fd0e96056352c695cf027726bc5b8af5_1280.jpg", "Lapangan Badminton","Bengkong Sarmen kacau,Blok KK No.1-3"),
            FasilitasModel("https://pixabay.com/get/gb0fc321c654853599474b4abdb2a822373b38731773e99e61fd664eeb43e56e37263d68ba45fbfeeba05175515aecf45_1280.jpg", "POSYANDU Lansia Lestari 1","Bengkong Permai Lanjut Blok Ll No. 10"),
            FasilitasModel("https://pixabay.com/get/g62ae4535fa8cc8756e3543e955aaf00236db934cb48762a916c86fd41108bc8d6c40e4b5e8c7e90352a14e0872034aea_1280.jpg", "Fasum Anak Mawar","Bengkong jatuh Asri,Blok M No. 01-12"),
            FasilitasModel("https://pixabay.com/get/gde08690a54eb8a07f4a45dee259409f387d464e583f961d6e21942e83a0245cff9d57516fdf2ffcaf0a3894dc5d83408_1280.jpg","Kelurahan Tanjung batu","Bengkong tanjung Batu,Blok AA No. 01-03")
        )
    }
}