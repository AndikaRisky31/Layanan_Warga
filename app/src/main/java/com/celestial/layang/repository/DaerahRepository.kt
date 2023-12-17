package com.celestial.layang.repository

import com.celestial.layang.api.ApiClient
import com.celestial.layang.api.ApiService
import com.celestial.layang.model.DataKabupaten
import com.celestial.layang.model.DataKecamatan
import com.celestial.layang.model.DataKelurahan
import com.celestial.layang.model.DataProvinsi
import retrofit2.Call

class DaerahRepository {
    private val apiService: ApiService = ApiClient.apiService

    fun getProvinces(): Call<List<DataProvinsi>> = apiService.getProvinces()

    fun getRegencies(provinceId: String): Call<List<DataKabupaten>> = apiService.getRegencies(provinceId)

    fun getDistricts(regencyId: String): Call<List<DataKecamatan>> = apiService.getDistricts(regencyId)

    fun getVillages(districtId: String): Call<List<DataKelurahan>> = apiService.getVillages(districtId)
}
