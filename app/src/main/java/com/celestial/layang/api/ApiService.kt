package com.celestial.layang.api

import com.celestial.layang.model.LoginRequest
import com.celestial.layang.model.LoginResponse
import com.celestial.layang.model.updateResponse
import com.celestial.layang.profile.ProfileModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.PUT

interface ApiService {
/*
    // Menggunakan @POST untuk menandai metode POST
    @POST("users/create")
    fun addUser(@Body user: User): Call<ResponseObject>
*/
    @POST("user/login")
    fun login(@Body body: LoginRequest): Call<LoginResponse>

    @PUT("user/update")
    fun updateUser(@Body body:ProfileModel):Call<updateResponse>
}
