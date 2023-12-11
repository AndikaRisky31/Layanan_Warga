package com.celestial.layang.model

import com.celestial.layang.profile.ProfileModel
import java.time.LocalDate

data class UserData(
    val user_id: Int,
    val kelurahan_id:Int,
    val username:String,
    val nomor:String,
    val email:String,
    val alamat:String,
    val tempatLahir: String,
    val TanggalLahir: String
)

data class LoginResponse(
    val message: String,
    val data: UserData,
)
data class LoginRequest(
    val username: String,
    val userPassword: String,
)

data class updateResponse(
    val message: String,
    val data: ProfileModel
)
