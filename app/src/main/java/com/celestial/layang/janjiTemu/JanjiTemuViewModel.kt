package com.celestial.layang.janjiTemu

import androidx.lifecycle.ViewModel

class JanjiTemuViewModel : ViewModel() {
    private val kontakList: List<KontakModel> = listOf(
        KontakModel(
            1,
            "https://pixabay.com/get/g4063bc9f77ff9bc64834769abdf16dc67495d925d4a6ec50d25da7fb5910a7b86057e3a2e3410139698a94bda2726307_1280.jpg",
            "John Doe",
            "Pegawai",
            "123456789",
            "john.doe@example.com",
            "Jalan Contoh No. 123",
            "Senin, 25 November 2023 - 10:00 AM"
        ),
        KontakModel(
            2,
            "https://pixabay.com/get/g45c64cf05da64a2897ef6a5f727534150b7d298c94e42643100b1acb1c434ad590093a253b3fe9cb343da71eab6f37b3_1280.jpg",
            "Jane Smith",
            "Manajer",
            "987654321",
            "jane.smith@example.com",
            "Jalan Lain No. 456",
            "Selasa, 26 November 2023 - 2:30 PM"
        ),
        KontakModel(
            3,
            "https://pixabay.com/get/gd97744bf57b12509198aab90f70c3fa07c826d8de2ce3f7279ccade1f73a77a53e44d39708b343f8fbd49dfe3f29f15e_1280.jpg",
            "Alice Johnson",
            "Analisis",
            "456789123",
            "alice.johnson@example.com",
            "Jalan Aja No. 789",
            "Rabu, 27 November 2023 - 3:45 PM"
        ),
        KontakModel(
            4,
            "https://pixabay.com/get/ge4129d76f83f91e7791542000990faf2f2c7cd2db1ebe9f175d62cb50e08a5fa7d3789ec8d71a60f8111f8592bde5947_1280.jpg",
            "Bob Williams",
            "Designer",
            "789123456",
            "bob.williams@example.com",
            "Jalan Terus No. 321",
            "Kamis, 28 November 2023 - 11:15 AM"
        ),
        // Tambahkan data kontak lainnya sesuai kebutuhan
    )

    fun getFasilitasList(): List<KontakModel> {
        return kontakList
    }

    // Fungsi untuk mendapatkan kontak berdasarkan ID
    fun getKontakById(kontakId: Int): KontakModel? {
        return kontakList.find { it.id == kontakId }
    }
}
