package com.celestial.layang.repository

import com.celestial.layang.api.ApiPublicClient
import com.celestial.layang.api.ApiPublicService
import com.celestial.layang.api.DataKabupaten
import com.celestial.layang.api.DataKecamatan
import com.celestial.layang.api.DataKelurahan
import com.celestial.layang.api.DataProvinsi
import retrofit2.Call

class DaerahRepository(private val apiPublicService: ApiPublicService) {

    fun getProvinces(): Call<List<DataProvinsi>> {
        return apiPublicService.getProvinces()
    }

    fun getRegencies(provinceId: String): Call<List<DataKabupaten>> {
        return apiPublicService.getRegencies(provinceId)
    }

    fun getDistricts(regencyId: String): Call<List<DataKecamatan>> {
        return apiPublicService.getDistricts(regencyId)
    }

    fun getVillages(districtId: String): Call<List<DataKelurahan>> {
        return apiPublicService.getVillages(districtId)
    }

    companion object {
        private var instance: DaerahRepository? = null

        fun getInstance(): DaerahRepository {
            return instance ?: synchronized(this) {
                instance ?: buildRepository().also { instance = it }
            }
        }

        private fun buildRepository(): DaerahRepository {
            return DaerahRepository(ApiPublicClient.apiPublicService)
        }
    }
}
