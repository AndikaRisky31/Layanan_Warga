package com.celestial.layang.model

import com.celestial.layang.janjiTemu.KontakModel

data class RequestResponse(
    val data:String
)
//LOGIN
data class LoginRequest(
    val email: String,
    val password: String,
)
data class LoginResponse(
    val message: String,
    val data: UserData,
)

//UPDATE USER
data class UpdateResponse(
    val message: String,
    val data: UserData
)
data class UserRequest(
    val user_id: Int
)
//AGENDA
data class KelurahanIdRequest(
    val kelurahan_id: String
    )

data class AgendaResponse(
    val message: String,
    val data:List<AgendaItem>
)
data class ArticleRequest(
    val page:Int,
    val pageSize:Int
)

data class ArticleResponse(
    val message: String,
    val data:List<ArticleModel>
)
data class ArticleSize(
    val size: Int
)
data class AdminResponse(
    val message: String,
    val data: List<KontakModel>
)
data class IdRequest(
    val id:Int
)
data class RegisterResponse(
    val success : Boolean,
    val message: String,
    val data:UserData
)

data class PengajuanRequest(
    val pengajuan_id: PengajuanData
)
data class PengajuanResponse(
    val success: Boolean,
    val message: String,
    val data: PengajuanData
)
data class LaporanRequest(
    val laporan_id: LaporanData
)
data class LaporanResponse(
    val success: Boolean,
    val message: String,
    val data: LaporanData
)
data class CheckEmailRequest(
    val email: String
)
data class CheckEmailResponse(
    val status : Boolean,
    val message: String
)


class ApiException(message: String) : Exception(message)
