package com.celestial.layang.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class BerandaViewModel : ViewModel() {
    // Gunakan MutableLiveData untuk data yang dapat berubah
    val beritaList = MutableLiveData<List<BeritaModel>>()

    init {
        // Isi data berita saat ViewModel diinisialisasi
        beritaList.value = listOf(
            BeritaModel("Admin 1", "Judul Berita 1", "halooo111oo1", "Deskripsi Berita 1"),
            BeritaModel("Admin 2", "Judul Berita 2", "halll002222", "Deskripsi Berita 2"),
            BeritaModel("Admin 3", "Judul berita 3", "hallooooo3333", "Deskripsi Berita 3")
        )
    }
}
