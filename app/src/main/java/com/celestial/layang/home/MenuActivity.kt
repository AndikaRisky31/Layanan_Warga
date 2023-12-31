package com.celestial.layang.home

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.celestial.layang.R
import com.celestial.layang.databinding.ActivityMenuBinding
import com.celestial.layang.lapor.LaporActivity
import com.celestial.layang.layanan.LayananFragment
import com.celestial.layang.notifikasi.NotifikasiFragment
import com.celestial.layang.profile.ProfileFragment

class MenuActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMenuBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_menu)
        val navigateToProfile = intent.getBooleanExtra("navigateToProfile", false)
        if(navigateToProfile){
            binding.bottomNavigationView.selectedItemId = R.id.navigation_Profile
            supportFragmentManager.beginTransaction()
                .replace(
                    R.id.fragment_container,
                    ProfileFragment()
                )
                .commit()
            intent.removeExtra("navigateToProfile")
        }else {
            supportFragmentManager.beginTransaction()
                .replace(
                    R.id.fragment_container,
                    BerandaFragment()
                )
                .commit()
        }
        binding.bottomNavigationView.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.navigation_Beranda -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, BerandaFragment())
                        .commit()
                    true
                }

                R.id.navigation_Layanan -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, LayananFragment())
                        .commit()
                    true
                }R.id.navigation_Lapor -> {
                    // Tampilkan activity lapor
                    intent = Intent(this,LaporActivity::class.java)
                    startActivity(intent)
                    true
                }

                R.id.navigation_Notifikasi -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, NotifikasiFragment())
                        .commit()
                    true
                }

                R.id.navigation_Profile -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, ProfileFragment())
                        .commit()

                    true
                }
                else -> false
            }
        }
    }
}