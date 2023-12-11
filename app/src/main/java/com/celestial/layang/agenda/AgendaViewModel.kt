package com.celestial.layang.agenda

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.celestial.layang.model.AgendaItem
import com.celestial.layang.repository.AgendaRepository
import com.celestial.layang.repository.ApiException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AgendaViewModel(private val repository: AgendaRepository) : ViewModel() {

    private val _agendaList = MutableLiveData<List<AgendaItem>>()
    val agendaList: LiveData<List<AgendaItem>> get() = _agendaList


    // Use suspend function directly within the coroutine scope
    fun getAgendaByKelurahanId(idKelurahan: String) {
        viewModelScope.launch {
            try {
                // Call the repository function directly within the coroutine
                val result = repository.getAgendaByKelurahanId(idKelurahan)
                // Update LiveData with the result
                _agendaList.value = result
            } catch (e: ApiException) {
                // Handle specific ApiException
                Log.e("AgendaViewModel", "ApiException: ${e.message}", e)
                // Notify the user or take other appropriate action
            } catch (e: Exception) {
                // Handle general exceptions
                Log.e("AgendaViewModel", "Error fetching agenda: ${e.message}", e)
                // Notify the user or take other appropriate action
            }
        }
    }
}

