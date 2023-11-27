package com.celestial.layang.profile

import androidx.lifecycle.ViewModel
import androidx.databinding.ObservableField

class ProfileEditViewModel : ViewModel() {

    private val profileRepository = ProfileRepository()
    private lateinit var profileData:ProfileModel

    val profile = ObservableField<ProfileModel>()

    // Fungsi untuk mengambil data profil dari repository
    fun fetchProfileData(userId: String) {
        profileData = profileRepository.getProfile(userId)

        profile.set(profileData)
    }

    // Fungsi untuk menyimpan data
    fun saveProfileData() {
        val newData = ProfileModel(
            id = profile.get()?.id ?: 0, // Sesuaikan dengan struktur ProfileModel
            Nama = profile.get()?.Nama ?: "",
            Nomor = profile.get()?.Nomor ?: "",
            Email = profile.get()?.Email ?: "",
            Alamat = profile.get()?.Alamat ?: "",
            pangkat = profile.get()?.pangkat ?: "",
            tempatLahir = profile.get()?.tempatLahir ?: "",
            TanggalLahir = profileData.TanggalLahir
        )
        saveProfile(newData)
    }

    private fun saveProfile(profileData: ProfileModel) {
        // TODO: menambahkan metode kemana data profile baru disimpan
    }
}
