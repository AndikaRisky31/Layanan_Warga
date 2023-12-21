package com.celestial.layang.repository

import com.celestial.layang.model.PengajuanData

class TemporaryDataRepository {
    private var halamanPertamaData: PengajuanData? = null

    fun setHalamanPertamaData(data: PengajuanData) {
        halamanPertamaData = data
    }

    fun getHalamanPertamaData(): PengajuanData? {
        return halamanPertamaData
    }

    fun clearTemporaryData() {
        halamanPertamaData = null
    }
}
