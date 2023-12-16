package com.celestial.layang.janjiTemu

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.celestial.layang.model.ApiException
import com.celestial.layang.repository.AdminRepository
import kotlinx.coroutines.launch

class JanjiTemuViewModel(private val repository: AdminRepository) : ViewModel() {

    private val _kontakList = MutableLiveData<List<KontakModel>>()
    val kontakList: LiveData<List<KontakModel>> get() = _kontakList

    fun getAgendaByKelurahanId(idKelurahan: String) {
        viewModelScope.launch {
            try {
                val result = repository.getAdminByKelurahanId(idKelurahan)
                _kontakList.value = result
            } catch (e: ApiException) {
                Log.e("AgendaViewModel", "ApiException: ${e.message}", e)
            } catch (e: Exception) {
                Log.e("AgendaViewModel", "Error fetching agenda: ${e.message}", e)
            }
        }
    }
}

