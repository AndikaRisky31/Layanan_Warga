package com.celestial.layang.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

object ApiPublicClient {
    private const val BASE_URL = "https://emsifa.github.io/api-wilayah-indonesia/api/"

    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()

    val apiPublicService: ApiPublicService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiPublicService::class.java)
    }
}
interface ApiPublicService {

    @GET("provinces.json")
    fun getProvinces(): Call<List<DataProvinsi>> // Ganti dengan model yang sesuai

    @GET("regencies/{provinceId}.json")
    fun getRegencies(@Path("provinceId") provinceId: String): Call<List<DataKabupaten>>

    @GET("districts/{regencyId}.json")
    fun getDistricts(@Path("regencyId") regencyId: String): Call<List<DataKecamatan>>

    @GET("villages/{districtId}.json")
    fun getVillages(@Path("districtId") districtId: String): Call<List<DataKelurahan>>
}

data class DataProvinsi(
    val id : String,
    val name : String
)

data class DataKabupaten(
    val id : String,
    val province_id:String,
    val name:String
)
data class DataKecamatan(
    val id :String,
    val regency_id:String,
    val name :String
)
data class DataKelurahan(
    val id:String,
    val district_id:String,
    val name:String
)
