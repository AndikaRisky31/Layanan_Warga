package com.celestial.layang.lapor

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.celestial.layang.api.ApiService
import com.celestial.layang.repository.LaporRepository

class LaporViewModelFactory(private val laporRepository: LaporRepository) : ViewModelProvider.Factory {
    fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LaporViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return LaporViewModel(laporRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}