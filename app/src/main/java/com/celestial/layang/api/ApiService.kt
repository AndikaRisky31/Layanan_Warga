package com.celestial.layang.api

import com.celestial.layang.model.AdminResponse
import com.celestial.layang.model.KelurahanIdRequest
import com.celestial.layang.model.AgendaResponse
import com.celestial.layang.model.ArticleRequest
import com.celestial.layang.model.ArticleResponse
import com.celestial.layang.model.IdRequest
import com.celestial.layang.model.LoginRequest
import com.celestial.layang.model.LoginResponse
import com.celestial.layang.model.UpdateResponse
import com.celestial.layang.model.UserData
import com.celestial.layang.model.UserRequest
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.PUT

interface ApiService {
    /*
    // Menggunakan @POST untuk menandai metode POST
    @POST("users/create")
    fun addUser(@Body user: User): Call<ResponseObject>
*/
    @POST("auth/login")
    fun login(@Body body: LoginRequest): Call<LoginResponse>

    @PUT("users/update")
    fun updateUser(@Body body: UserData): Call<UpdateResponse>

    @POST("users/data")
    suspend fun getProfileData(@Body request: UserRequest): Response<UpdateResponse>

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

}
