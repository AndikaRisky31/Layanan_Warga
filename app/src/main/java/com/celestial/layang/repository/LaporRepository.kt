package com.celestial.layang.repository

import com.celestial.layang.api.ApiClient
import com.celestial.layang.api.ApiService
import com.celestial.layang.lapor.LaporActivity
import com.celestial.layang.model.LaporanRequest
import com.celestial.layang.model.LaporanResponse
import retrofit2.Response

class LaporRepository(laporActivity: LaporActivity) {
    private val apiService: ApiService = ApiClient.apiService

    suspend fun saveLaporan(laporanRequest: LaporanRequest): Response<LaporanResponse> {
        return apiService.saveLaporan(laporanRequest)
    }
}