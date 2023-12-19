package com.celestial.layang.layanan.pengajuan.pengajuanidentity

import androidx.lifecycle.ViewModel
import com.celestial.layang.model.PengajuanData
import com.celestial.layang.repository.TemporaryDataRepository

class PengajuanViewModel : ViewModel() {

    private val temporaryDataRepository = TemporaryDataRepository()

    fun setHalamanPertamaData(data: PengajuanData) {
        temporaryDataRepository.setHalamanPertamaData(data)
    }

    fun getHalamanPertamaData(): PengajuanData? {
        return temporaryDataRepository.getHalamanPertamaData()
    }

    fun clearTemporaryData() {
        temporaryDataRepository.clearTemporaryData()
    }
}

