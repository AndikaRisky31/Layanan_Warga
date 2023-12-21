package com.celestial.layang.fasilitas

import androidx.lifecycle.ViewModel

class FasilitasViewModel :ViewModel(){
    fun getFasilitasList(): List<FasilitasModel> {
        return listOf(
            FasilitasModel(
                "https://api.ayo.co.id/image/venue/168398058571403.image_cropper_1683980470943_large.jpg",
                "Lapangan Badminton",
                "Bengkong Sarmen kacau,Blok KK No.1-3"),
            FasilitasModel(
                "https://terung.magetan.go.id/media/img/berita/berita_6032625e43189f74d3.43494173.jpg",
                "POSYANDU Lansia Lestari 1",
                "Dk. Wijang Blok Ll No. 10"),
            FasilitasModel(
                "https://suaranahdliyin.com/wp-content/uploads/2023/09/IMG-20230916-WA0020-696x392.jpg",
                "Kelurahan Karangtalun",
                "Jl Ngawen-Kamolan, No. 01"),
            FasilitasModel(
                "https://lh3.googleusercontent.com/p/AF1QipM8sfSjtAcyQfR4ih2ES23UAM8Iob9oEuapV9BP=s0",
                "Lapangan Desa Wijang",
                "Dk Wijang"),
        )
    }
}