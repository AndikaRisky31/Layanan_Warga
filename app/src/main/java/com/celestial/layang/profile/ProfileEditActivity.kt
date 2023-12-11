package com.celestial.layang.profile

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
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
        binding = DataBindingUtil.setContentView(this, R.layout.activity_profile_edit)

        val profileRepository: ProfileRepository by lazy {
            ProfileRepository(this)
        }

        val viewModelFactory = ProfileEditViewModelFactory(profileRepository)

        viewModel = ViewModelProvider(this, viewModelFactory)[ProfileEditViewModel::class.java]

        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        viewModel.loadProfile()
        val data = profileRepository.getProfile()
        binding.saveButton.setOnClickListener {
            viewModel.saveProfile(
                ProfileModel(
                    id = data.id,
                    Nama = binding.inUsername.text.toString(),
                    Nomor = binding.inNomor.text.toString(),
                    Email = binding.inEmail.text.toString(),
                    Alamat = binding.inAlamat.text.toString(),
                    pangkat = "Warga",
                    tempatLahir = data.tempatLahir,
                    TanggalLahir = data.TanggalLahir
                    )
            )
            navigateBackToProfileFragment()
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
