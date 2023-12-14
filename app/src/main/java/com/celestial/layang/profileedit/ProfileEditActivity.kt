package com.celestial.layang.profileedit

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.celestial.layang.R
import com.celestial.layang.databinding.ActivityProfileEditBinding
import com.celestial.layang.home.MenuActivity
import com.celestial.layang.model.UserData
import com.celestial.layang.repository.UserDataRepository
import kotlinx.coroutines.launch

class ProfileEditActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfileEditBinding
    private lateinit var viewModel: ProfileEditViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_profile_edit)

        val userDataRepository: UserDataRepository by lazy {
            UserDataRepository(this)
        }

        val viewModelFactory = ProfileEditViewModelFactory(userDataRepository)

        viewModel = ViewModelProvider(this, viewModelFactory)[ProfileEditViewModel::class.java]

        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        viewModel.loadProfile()

        viewModel.viewModelScope.launch {
            try {
                val data = userDataRepository.userData()

                binding.saveButton.setOnClickListener {
                    viewModel.saveUserData(
                        UserData(
                            user_id = data.user_id,
                            kelurahan_id = data.kelurahan_id,
                            username = binding.inUsername.text.toString(),
                            nomor = binding.inNomor.text.toString(),
                            email = binding.inEmail.text.toString(),
                            alamat = binding.inAlamat.text.toString(),
                            tempatLahir = data.tempatLahir,
                            TanggalLahir = data.TanggalLahir
                        )
                    )
                    navigateBackToProfileFragment()
                }
            } catch (e: Exception) {
                // Handle exceptions, show error message, log, etc.
                Log.e("Error", "Error fetching profile data: ${e.message}", e)
            }
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
