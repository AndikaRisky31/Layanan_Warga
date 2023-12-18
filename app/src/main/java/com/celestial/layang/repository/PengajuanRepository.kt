package com.celestial.layang.repository

import com.celestial.layang.api.ApiClient
import com.celestial.layang.api.ApiService
import com.celestial.layang.model.PengajuanRequest
import com.celestial.layang.model.PengajuanResponse
import com.celestial.layang.model.UserData
import retrofit2.Response

class PengajuanRepository {

    private val apiService: ApiService = ApiClient.apiService

    suspend fun savePengajuan(pengajuanRequest: PengajuanRequest): Response<PengajuanResponse> {
        return apiService.savePengajuan(pengajuanRequest)
    }

}
