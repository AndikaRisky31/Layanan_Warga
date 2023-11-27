package com.celestial.layang.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProfileViewModel(private val repository: ProfileRepository) : ViewModel() {

    private val _profile = MutableLiveData<ProfileModel>()
    val profile: LiveData<ProfileModel>
        get() = _profile

    fun loadProfile(userId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val result = repository.getProfile(userId)
                withContext(Dispatchers.Main) {
                    _profile.value = result
                }
            } catch (e: Exception) {
                // Handle exception (jika diperlukan)
            }
        }
    }
}
