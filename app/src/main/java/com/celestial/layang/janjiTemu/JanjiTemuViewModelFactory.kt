package com.celestial.layang.janjiTemu

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.celestial.layang.repository.AdminRepository


class JanjiTemuViewModelFactory(private val adminRepository: AdminRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(JanjiTemuViewModel::class.java)) {
            return JanjiTemuViewModel(adminRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
