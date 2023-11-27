package com.celestial.layang.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.celestial.layang.R
import com.celestial.layang.databinding.ActivityMenuBinding
import com.celestial.layang.layanan.LayananFragment
import com.celestial.layang.notifikasi.NotifikasiFragment
import com.celestial.layang.profile.ProfileFragment

class MenuActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMenuBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_menu
        ) // Sesuaikan dengan nama layout XML Anda
        supportFragmentManager.beginTransaction()
            .replace(
                R.id.fragment_container,
                BerandaFragment()
            ) // Ganti dengan fragmen awal yang diinginkan
            .commit()
        binding.bottomNavigationView.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.navigation_Beranda -> {
                    // Tampilkan fragmen home di tengah
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, BerandaFragment())
                        .commit()
                    true
                }

                R.id.navigation_Layanan -> {
                    // Tampilkan fragmen layanan di tengah
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, LayananFragment())
                        .commit()
                    true
                }

                R.id.navigation_Notifikasi -> {
                    // Tampilkan fragmen notifikasi di tengah
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