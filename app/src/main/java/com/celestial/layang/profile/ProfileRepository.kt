package com.celestial.layang.profile

import android.content.Context
import android.util.Log
import com.celestial.layang.repository.UserPreferences
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class ProfileRepository(context: Context) {
    private val userPreferences: UserPreferences by lazy {
        UserPreferences(context.getSharedPreferences("User_Data", Context.MODE_PRIVATE))
    }

    fun getProfile(): ProfileModel {
        val data = userPreferences.getUserData()
        Log.e("data dari share", data.toString())

        return ProfileModel(
            id = data.user_id,
            Nama = data.username,
            Nomor = data.nomor,
            Email = data.email,
            Alamat = data.alamat,
            pangkat = "Warga",
            tempatLahir = data.tempatLahir,
            TanggalLahir = convertTime(data.TanggalLahir)
        )
    }

    private fun convertTime(timeString: String): LocalDate {
        // Menggunakan DateTimeFormatter untuk menguraikan string timestamp
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSX")
        return LocalDate.parse(timeString, formatter)
    }
}
