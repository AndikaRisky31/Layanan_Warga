package com.celestial.layang.profile

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.celestial.layang.api.ApiClient
import com.celestial.layang.api.ApiService
import com.celestial.layang.model.updateResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDate

class ProfileEditViewModel(private val profileRepository: ProfileRepository) : ViewModel() {

    private lateinit var apiService: ApiService
    private val _profile = MutableLiveData<ProfileModel>()
    val profile: LiveData<ProfileModel> get() = _profile

    fun loadProfile() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val result = profileRepository.getProfile()
                withContext(Dispatchers.Main) {
                    _profile.value = result
                }
            } catch (e: Exception) {
                // Handle exception (jika diperlukan)
            }
        }
    }


    fun saveProfile(profileData: ProfileModel) {
        apiService = ApiClient.apiService
        val call: Call<updateResponse> = apiService.updateUser(profileData)

        // Enqueue the network request asynchronously
        call.enqueue(object : Callback<updateResponse> {
            override fun onResponse(call: Call<updateResponse>, response: Response<updateResponse>) {
                if (response.isSuccessful) {
                    Log.e("Update profileee", "Update successful - ${response.message()}")
                } else {
                    Log.e("Update profileee", "Update unsuccessful - ${response.code()}")
                    // Handle unsuccessful response
                    // You can log the error or perform appropriate actions
                }
            }

            override fun onFailure(call: Call<updateResponse>, t: Throwable) {
                Log.e("Update Profileee", "Failure: ${t.message}")
                // Handle failure appropriately, e.g., retrying the request or showing an error message
            }
        })
    }

}
