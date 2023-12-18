package com.celestial.layang.layanan.pengajuan.pengajuanidentity

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.celestial.layang.api.ApiService
import com.celestial.layang.model.PengajuanData
import com.celestial.layang.model.PengajuanRequest
import com.celestial.layang.model.PengajuanResponse
import com.celestial.layang.model.UserData
import com.celestial.layang.repository.PengajuanRepository
import com.celestial.layang.repository.UserDataRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response

class PengajuanIdentityViewModel(private val pengajuanRepository: PengajuanRepository) : ViewModel() {

    private lateinit var apiService: ApiService

    private val _profile = MutableLiveData<UserData>()
    val profile: LiveData<UserData> get() = _profile
    fun loadProfile(userDataRepository: UserDataRepository) {
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

    fun savePengajuanIdentity(pengajuanData: PengajuanData) {
        val pengajuanRequest = PengajuanRequest(pengajuanData)
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response: Response<PengajuanResponse> = apiService.savePengajuan(pengajuanRequest)
                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) {
                        Log.e(
                            "Create Pengajuan",
                            "Create successful - ${response.body()?.data.toString()}"
                        )
                    } else {
                        Log.e("Create Pengajuan", "Create unsuccessful - ${response.code()}")
                        // Handle unsuccessful response
                        // You can log the error or perform appropriate actions
                    }
                }
            } catch (t: Throwable) {
                Log.e("Creat Pengajuan", "Failure: ${t.message}")
                // Handle failure appropriately, e.g., retrying the request or showing an error message
            }
        }
    }

}
