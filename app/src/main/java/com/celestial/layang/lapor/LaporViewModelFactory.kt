package com.celestial.layang.lapor

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.celestial.layang.repository.LaporRepository

class LaporViewModelFactory(private val laporRepository: LaporRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LaporViewModel::class.java)) {
            return LaporViewModel(laporRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}