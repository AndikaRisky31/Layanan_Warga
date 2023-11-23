package com.celestial.layang.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class BerandaViewModel : ViewModel() {
    // Gunakan MutableLiveData untuk data yang dapat berubah
    val beritaList = MutableLiveData<List<BeritaModel>>()

    init {
        // Isi data berita saat ViewModel diinisialisasi
        beritaList.value = listOf(
            BeritaModel("https://pixabay.com/get/g331ea08842c650160f75fb9a9417245d3c2f4d56db8e07dde7ee63b07f10c334fd0e96056352c695cf027726bc5b8af5_1280.jpg",
                "Judul Berita 1", "Senin,6 nov 2023", "Deskripsi Berita 1"),
            BeritaModel("https://pixabay.com/get/gb0fc321c654853599474b4abdb2a822373b38731773e99e61fd664eeb43e56e37263d68ba45fbfeeba05175515aecf45_1280.jpg",
                "Judul Berita 2", "kamis, 5 Okt 2023", "Deskripsi Berita 2"),
            BeritaModel("https://pixabay.com/get/g62ae4535fa8cc8756e3543e955aaf00236db934cb48762a916c86fd41108bc8d6c40e4b5e8c7e90352a14e0872034aea_1280.jpg",
                "Judul berita 3", "Minggu,24 Sept 2023", "Deskripsi Berita 3")
        )
    }
}
