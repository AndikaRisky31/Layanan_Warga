package com.celestial.layang.profile

import java.time.LocalDate


class ProfileRepository {
    fun getProfile(userId: String): ProfileModel {
        // Implementasi pengambilan data dari API
        // Misalnya, return data dummy sementara
        return ProfileModel(
            id = 1,
            Nama = "John Doe",
            Nomor = "123456789",
            Email = "john.doe@example.com",
            Alamat = "123 Main Street",
            pangkat = "Warga",
            tempatLahir = "City",
            TanggalLahir = LocalDate.of(1990, 1, 1) // Sesuaikan dengan tanggal lahir yang sesuai
        )
    }
}
