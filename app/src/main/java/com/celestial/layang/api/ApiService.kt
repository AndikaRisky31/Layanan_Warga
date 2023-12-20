package com.celestial.layang.api

import com.celestial.layang.model.AdminResponse
import com.celestial.layang.model.AgendaResponse
import com.celestial.layang.model.ArticleModel
import com.celestial.layang.model.ArticleRequest
import com.celestial.layang.model.ArticleResponse
import com.celestial.layang.model.ArticleSize
import com.celestial.layang.model.CheckEmailRequest
import com.celestial.layang.model.CheckEmailResponse
import com.celestial.layang.model.DataKabupaten
import com.celestial.layang.model.DataKecamatan
import com.celestial.layang.model.DataKelurahan
import com.celestial.layang.model.DataProvinsi
import com.celestial.layang.model.IdRequest
import com.celestial.layang.model.KelurahanIdRequest
import com.celestial.layang.model.LaporanResponse
import com.celestial.layang.model.LoginRequest
import com.celestial.layang.model.LoginResponse
import com.celestial.layang.model.PengajuanResponse
import com.celestial.layang.model.RegisterResponse
import com.celestial.layang.model.UpdateResponse
import com.celestial.layang.model.UserData
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path

interface ApiService {

    @POST("users/create")
    fun addUser(@Body user: UserData): Call<RegisterResponse>
    @POST("auth/login")
    fun login(@Body body: LoginRequest): Call<LoginResponse>

    @POST("auth/register")
    fun checkEmail(@Body body: CheckEmailRequest):Call<CheckEmailResponse>

    @PATCH("users/{id}")
    fun updateUser(@Path("id") user_id: String,@Body userData: UserData ): Call<UpdateResponse>

    @GET("users/{user_id}")
    suspend fun getProfileData(@Path("user_id") user_id : String): Response<UpdateResponse>

    @POST("agenda/kelurahan_id")
    suspend fun getAgendaList(@Body request: KelurahanIdRequest): Response<AgendaResponse>

    @POST("articles/latest")
    suspend fun getLatestArticles(@Body body:ArticleSize): Response<List<ArticleModel>>
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

    @Multipart
    @POST("pengajuan/create")
    fun createPengajuan(
        @Part("user_id") userId: RequestBody,
        @Part("namaLengkap") namaLengkap: RequestBody,
        @Part("noNik") noNik: RequestBody,
        @Part("agama") agama: RequestBody,
        @Part("status") status: RequestBody,
        @Part("alamat") alamat: RequestBody,
        @Part("jenisSurat") jenisSurat: RequestBody,
        @Part fileKTP: MultipartBody.Part,
        @Part fileKK: MultipartBody.Part
    ): Call<PengajuanResponse>

    @Multipart
    @POST("laporan/create") // Replace with your actual endpoint
    fun uploadLaporan(
        @Part("user_id") userId: RequestBody,
        @Part("lokasi_laporan") lokasi: RequestBody,
        @Part("jenis_laporan") jenis: RequestBody,
        @Part("deskripsi") deskripsi: RequestBody,
        @Part bukti: MultipartBody.Part
    ):Call<LaporanResponse>
}
