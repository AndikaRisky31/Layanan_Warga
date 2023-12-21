package com.celestial.layang.notifikasi

import androidx.lifecycle.ViewModel
import java.time.LocalDateTime

class NotifikasiViewModel : ViewModel() {
    private val notifikasiList: List<NotifikasiModel> = listOf(
        NotifikasiModel("Surat pengantar berhasil terkirim", "Silahkan tunggu update selanjutnya", LocalDateTime.now()),
        NotifikasiModel("Surat pengantar sudah diproses", "Silahkan mengambil berkas di kelurahan", LocalDateTime.now()),
    )

    fun getNotifikasiList(): List<NotifikasiModel> {
        return notifikasiList
    }
}
