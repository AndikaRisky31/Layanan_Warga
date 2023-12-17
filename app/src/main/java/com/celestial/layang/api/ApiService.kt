package com.celestial.layang.api

import com.celestial.layang.model.AdminResponse
import com.celestial.layang.model.KelurahanIdRequest
import com.celestial.layang.model.AgendaResponse
import com.celestial.layang.model.ArticleRequest
import com.celestial.layang.model.ArticleResponse
import com.celestial.layang.model.DataKabupaten
import com.celestial.layang.model.DataKecamatan
import com.celestial.layang.model.DataKelurahan
import com.celestial.layang.model.DataProvinsi
import com.celestial.layang.model.IdRequest
import com.celestial.layang.model.LaporanData
import com.celestial.layang.model.LaporanRequest
import com.celestial.layang.model.LaporanResponse
import com.celestial.layang.model.LoginRequest
import com.celestial.layang.model.LoginResponse
import com.celestial.layang.model.PengajuanRequest
import com.celestial.layang.model.PengajuanResponse
import com.celestial.layang.model.UpdateResponse
import com.celestial.layang.model.UserData
import com.celestial.layang.model.UserRequest
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ApiService {
    /*
    // Menggunakan @POST untuk menandai metode POST
    @POST("users/create")
    fun addUser(@Body user: User): Call<ResponseObject>
*/
    @POST("auth/login")
    fun login(@Body body: LoginRequest): Call<LoginResponse>

    @PATCH("users/{id}")
    fun updateUser(@Path("id") user_id: String,@Body userData: UserData ): Call<UpdateResponse>

    @GET("users/{user_id}")
    suspend fun getProfileData(@Path("user_id") user_id : String): Response<UpdateResponse>

    @POST("agenda/kelurahan_id")
    suspend fun getAgendaList(@Body request: KelurahanIdRequest): Response<AgendaResponse>

    @POST("articles/latest")
    suspend fun getLatestArticles(@Body size:Int): Response<ArticleResponse>
    @POST("articles/page")
    suspend fun getArticlesByPage(@Body request: ArticleRequest): Response<ArticleResponse>

    @POST("admin/kelurahan_id")
    suspend fun getAdmin(@Body request: KelurahanIdRequest):Response<AdminResponse>

    @POST("admin/id")
    suspend fun getAdminById(@Body request: IdRequest):Response<AdminResponse>

    @GET("daerah/provinsi/all")
    fun getProvinces(): Call<List<DataProvinsi>>

    @GET("daerah/kabupaten/{provinceId}")
    fun getRegencies(@Path("provinceId") provinceId: String): Call<List<DataKabupaten>>

    @GET("daerah/kecamatan/{regencyId}")
    fun getDistricts(@Path("regencyId") regencyId: String): Call<List<DataKecamatan>>

    @GET("daerah/kelurahan/{districtId}")
    fun getVillages(@Path("districtId") districtId: String): Call<List<DataKelurahan>>

    @POST("pengajuan")
    suspend fun savePengajuan(@Body request: PengajuanRequest): Response<PengajuanResponse>

    @POST("laporan")
    suspend fun saveLaporan(@Body request: LaporanRequest): Response<LaporanResponse>
}
