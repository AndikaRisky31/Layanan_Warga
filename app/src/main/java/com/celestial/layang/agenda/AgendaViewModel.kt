package com.celestial.layang.agenda

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.celestial.layang.model.AgendaItem
import com.celestial.layang.model.ApiException
import com.celestial.layang.repository.AgendaRepository
import kotlinx.coroutines.launch

class AgendaViewModel(private val repository: AgendaRepository) : ViewModel() {

    private val _agendaList = MutableLiveData<List<AgendaItem>>()
    val agendaList: LiveData<List<AgendaItem>> get() = _agendaList

    fun getAgendaByKelurahanId(idKelurahan: String) {
        viewModelScope.launch {
            try {
                val result = repository.getAgendaByKelurahanId(idKelurahan)
                _agendaList.value = result
            } catch (e: ApiException) {
                Log.e("AgendaViewModel", "ApiException: ${e.message}", e)
            } catch (e: Exception) {
                Log.e("AgendaViewModel", "Error fetching agenda: ${e.message}", e)
            }
        }
    }
}

