package com.celestial.layang.layanan.pengajuan.pengajuanidentity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.celestial.layang.R
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
        val spinnerStatus: Spinner = binding.spinnerAgama

        ArrayAdapter.createFromResource(
            this,
            R.array.status_array, // Define an array resource in your `res/values/arrays.xml` file
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spinnerStatus.adapter = adapter
        }
    }

    // Handler untuk tombol lanjut ke halaman kedua
    private fun navigateToPengajuanBerkas() {
        val pengajuanData = createData()

        if (isDataValid(pengajuanData)) {
            val intent = Intent(this, PengajuanBerkasActivity::class.java)
            intent.putExtra("halamanPertamaData", pengajuanData)
            startActivity(intent)
        } else {
            // Show a message or perform other actions if the data is not valid
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
        }
    }

    private fun createData(): PengajuanData {
        val data = userPreferences.getUserData()
        return PengajuanData(
            pengajuan_id = null,
            user_id = data.user_id ?: 0,
            nama = binding.etNama.text.toString(),
            nik = binding.etNik.text.toString(),
            agama = binding.spinnerAgama.selectedItem.toString(),
            status =  binding.etStatus.text.toString(),
            alamat = binding.etAlamat.text.toString(),
            jenis = jenisSurat,
            imageKTP = "",
            imageKK = ""
        )
    }


    private fun isDataValid(pengajuanData: PengajuanData): Boolean {
        Log.e("hasil data",pengajuanData.toString())
        // Add your validation logic here
        return pengajuanData.nama.isNotBlank() &&
                pengajuanData.nik.isNotBlank() &&
                pengajuanData.agama.isNotBlank() &&
                pengajuanData.status.isNotBlank() &&
                pengajuanData.alamat.isNotBlank()
    }


}