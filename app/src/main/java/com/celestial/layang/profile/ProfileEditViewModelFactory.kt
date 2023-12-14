package com.celestial.layang.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.celestial.layang.profileedit.ProfileEditViewModel
import com.celestial.layang.repository.UserDataRepository

class ProfileEditViewModelFactory(private val repository: UserDataRepository) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProfileEditViewModel::class.java)) {
            return ProfileEditViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
