package com.celestial.layang.notifikasi

import androidx.lifecycle.ViewModel
import java.time.LocalDateTime

class NotifikasiViewModel : ViewModel() {
    private val notifikasiList: List<NotifikasiModel> = listOf(
        NotifikasiModel("Judul 1", "Keterangan 1", LocalDateTime.now()),
        NotifikasiModel("Judul 2", "Keterangan 2", LocalDateTime.now()),
        NotifikasiModel("Judul 3", "Keterangan 3", LocalDateTime.now())
        // Tambahkan data dummy sesuai kebutuhan
    )

    fun getNotifikasiList(): List<NotifikasiModel> {
        return notifikasiList
    }
}
