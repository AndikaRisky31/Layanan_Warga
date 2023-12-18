package com.celestial.layang.janjiTemu

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.celestial.layang.repository.AdminRepository
import kotlinx.coroutines.launch

class KontakViewModel(application: Application) : AndroidViewModel(application) {
    private val kontakRepository: AdminRepository = AdminRepository()

    private val _kontak = MutableLiveData<KontakModel>()
    val kontak: LiveData<KontakModel>
        get() = _kontak

    fun getKontakById(kontakId: Int) {
        viewModelScope.launch {
            try {
                val kontak = kontakRepository.getAdminById(kontakId)
                _kontak.value = kontak
            } catch (e: Exception) {
                // Handle the exception
            }
        }
    }
}
