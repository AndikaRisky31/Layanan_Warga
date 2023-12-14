package com.celestial.layang.profileedit

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.celestial.layang.api.ApiClient
import com.celestial.layang.api.ApiService
import com.celestial.layang.model.UpdateResponse
import com.celestial.layang.model.UserData
import com.celestial.layang.repository.UserDataRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfileEditViewModel(private val userDataRepository: UserDataRepository) : ViewModel() {

    private lateinit var apiService: ApiService
    private val _profile = MutableLiveData<UserData>()
    val profile: LiveData<UserData> get() = _profile

    fun loadProfile() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val result = userDataRepository.userData()
                withContext(Dispatchers.Main) {
                    _profile.value = result
                }
            } catch (e: Exception) {
                // Handle exception (jika diperlukan)
            }
        }
    }


    fun saveUserData(userData: UserData) {
        apiService = ApiClient.apiService
        val call: Call<UpdateResponse> = apiService.updateUser(userData)

        // Enqueue the network request asynchronously
        call.enqueue(object : Callback<UpdateResponse> {
            override fun onResponse(call: Call<UpdateResponse>, response: Response<UpdateResponse>) {
                if (response.isSuccessful) {
                    Log.e("Update profileee", "Update successful - ${response.body()?.data.toString()}")
                } else {
                    Log.e("Update profileee", "Update unsuccessful - ${response.code()}")
                    // Handle unsuccessful response
                    // You can log the error or perform appropriate actions
                }
            }

            override fun onFailure(call: Call<UpdateResponse>, t: Throwable) {
                Log.e("Update Profileee", "Failure: ${t.message}")
                // Handle failure appropriately, e.g., retrying the request or showing an error message
            }
        })
    }

}
