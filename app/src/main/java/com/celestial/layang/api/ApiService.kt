package com.celestial.layang.api

import com.celestial.layang.model.LoginResponse
import com.celestial.layang.model.ResponseObject
import com.celestial.layang.model.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    // Menggunakan @POST untuk menandai metode POST
    @POST("api/users/create")
    fun addUser(@Body user: User): Call<ResponseObject>

    @FormUrlEncoded
    @POST("api/login")
    fun login(
        @Field("username") username: String,
        @Field("password") password: String
    ): Call<LoginResponse>
}
