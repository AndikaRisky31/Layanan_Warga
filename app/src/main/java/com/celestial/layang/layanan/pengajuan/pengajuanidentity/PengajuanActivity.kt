package com.celestial.layang.layanan.pengajuan.pengajuanidentity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.celestial.layang.databinding.ActivityPengajuanIdentityBinding
import com.celestial.layang.model.PengajuanData
import com.celestial.layang.repository.UserPreferences

class PengajuanIdentityActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPengajuanIdentityBinding
    private val userPreferences: UserPreferences by lazy {
        UserPreferences(getSharedPreferences("User_Data", Context.MODE_PRIVATE))
    }
    private lateinit var jenisSurat:String
    // In PengajuanBerkasActivity (when retrieving)



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPengajuanIdentityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        jenisSurat = intent.getStringExtra("judul").toString()

        // Example: Handling button click
        binding.buttonNext.setOnClickListener {
            navigateToPengajuanBerkas()
        }
        binding.buttonBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }

    // Handler untuk tombol lanjut ke halaman kedua
    private fun navigateToPengajuanBerkas() {
        val pengajuanData = createData()
        val intent = Intent(this, PengajuanBerkasActivity::class.java)
        intent.putExtra("halamanPertamaData", pengajuanData)
        startActivity(intent)
    }

    private fun createData(): PengajuanData {
        val data = userPreferences.getUserData()
        return PengajuanData(
            pengajuan_id = null,
            user_id = (data.user_id ?: "") as Int, // Provide a default value if user_id is null
            nama = binding.etNama.text.toString(),
            nik = binding.etNik.text.toString().toInt(),
            ttl = binding.etTtl.text.toString(),
            agama = binding.etAgama.text.toString(),
            status = binding.etStatus.text.toString(),
            alamat = binding.etAlamat.text.toString(),
            jenis = jenisSurat,
            imageKTP = "", // Kosongkan karena gambar akan diunggah di halaman kedua
            imageKK = ""   // Kosong
        )
    }

}