package com.celestial.layang.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.celestial.layang.repository.UserDataRepository

class ProfileViewModelFactory(private val repository: UserDataRepository) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
     override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProfileViewModel::class.java)) {
            return ProfileViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
