package com.celestial.layang.profile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.celestial.layang.R
import com.celestial.layang.databinding.ActivityProfileEditBinding
import com.celestial.layang.home.MenuActivity

class ProfileEditActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfileEditBinding
    private lateinit var viewModel: ProfileEditViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Menginisialisasi Data Binding
        binding = DataBindingUtil.setContentView(this, R.layout.activity_profile_edit)

        // Menginisialisasi ViewModel
        viewModel = ViewModelProvider(this)[ProfileEditViewModel::class.java]

        // Mendapatkan ID pengguna dari sumber daya yang sesuai (misalnya, intent, shared preferences, dll.)
        val userId = "user123" // Gantilah dengan cara Anda mendapatkan ID pengguna

        // Menghubungkan ViewModel dengan layout menggunakan Data Binding
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        // Memuat data profil dari repository menggunakan ViewModel
        viewModel.fetchProfileData(userId)

        // Menangani tombol simpan
        binding.saveButton.setOnClickListener {
            // Memanggil fungsi saveProfileData di ViewModel
            viewModel.saveProfileData()

            navigateBackToProfileFragment()
            // Lakukan sesuatu setelah data disimpan, seperti menavigasi kembali atau menampilkan pesan sukses
        }
    }
    // Di dalam EditProfileActivity setelah menyimpan data
    private fun navigateBackToProfileFragment() {
        val intent = Intent(this, MenuActivity::class.java)
        intent.putExtra("navigateToProfile", true)
        startActivity(intent)
        finish()
    }

}
