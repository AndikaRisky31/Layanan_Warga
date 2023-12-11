package com.celestial.layang.model

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

data class UpdateResponse(
    val message: String,
    val data: UserData
)

data class AgendaItem(
    val agenda_id:Int,
    val kelurahan_id: Int,
    val judul:String,
    val imageURL:String,
    val tanggal:String,
    val tempat:String
)
data class AgendaRequest(
    val kelurahan_id: String
    )
data class AgendaResponse(
    val message: String,
    val data:List<AgendaItem>
)
data class UserRequest(
    val user_id: Int
)
