package com.celestial.layang.layanan.pengajuan.pengajuanidentity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.celestial.layang.repository.PengajuanRepository

class PengajuanIdentityViewModelFactory( private val repository: PengajuanRepository) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PengajuanIdentityViewModel::class.java)) {
            return PengajuanIdentityViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}