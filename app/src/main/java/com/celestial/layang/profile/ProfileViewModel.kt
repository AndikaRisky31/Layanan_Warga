package com.celestial.layang.profile

import android.content.Intent
import android.view.View
import androidx.appcompat.view.ContextThemeWrapper
import androidx.appcompat.widget.PopupMenu
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.celestial.layang.R
import com.celestial.layang.model.UserData
import com.celestial.layang.profileedit.ProfileEditActivity
import com.celestial.layang.repository.UserDataRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProfileViewModel(private val repository: UserDataRepository) : ViewModel() {

    private val _profile = MutableLiveData<UserData>()
    val profile: LiveData<UserData>
        get() = _profile

    fun loadProfile() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val result = repository.userData()
                withContext(Dispatchers.Main) {
                    _profile.value = result
                }
            } catch (e: Exception) {
                // Handle exception (jika diperlukan)
            }
        }
    }
    fun showPopupMenu(view: View) {
        val popupMenu = PopupMenu(ContextThemeWrapper(view.context, R.style.PopupMenuStyle), view)

        popupMenu.menuInflater.inflate(R.menu.profile_menu, popupMenu.menu)

        popupMenu.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.editProfile -> {
                    val intent = Intent(view.context, ProfileEditActivity::class.java)
                    view.context.startActivity(intent)
                    true
                }
                R.id.Setting -> {
                    val intent = Intent(view.context,UserSettingActivity::class.java)
                    view.context.startActivity(intent)
                    true
                }
                R.id.About -> {
                    val intent = Intent(view.context,AboutActivity::class.java)
                    view.context.startActivity(intent)
                    true
                }
                R.id.logout -> {
                    // Handle Logout click
                    // Navigasi ke halaman LogoutFragment atau lakukan aksi logout
                    true
                }
                else -> false
            }
        }

        popupMenu.show()
    }

}
