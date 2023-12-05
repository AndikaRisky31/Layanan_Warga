// YourActivity.kt
package com.celestial.layang.preLogin

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.celestial.layang.api.ApiClient
import com.celestial.layang.api.ApiService
import com.celestial.layang.databinding.ActivityRegisterBinding
import com.celestial.layang.model.ResponseObject
import com.celestial.layang.model.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var apiService: ApiService

    override fun onCreate(savedInstanceState: Bundle?) {
        apiService = ApiClient.apiService
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.linklogin.setOnClickListener{
            intent = Intent(this,LoginActivity::class.java)
            startActivity(intent)
        }
        binding.buttonMendaftar.setOnClickListener{
            addUser()
        }
    }
    private fun addUser() {
        val username = binding.inputUsername.toString()
        val email = binding.inputEmail.toString()
        val userPassword = binding.password.toString()

        // Membuat objek User
        val user = User(username = username, userpassword = userPassword, email = email)

        // Melakukan POST ke API
        val call = apiService.addUser(user)
        call.enqueue(object : Callback<ResponseObject> {
            override fun onResponse(call: Call<ResponseObject>, response: Response<ResponseObject>) {
                if (response.isSuccessful) {
                    Toast.makeText(this@RegisterActivity, "Berhasillll", Toast.LENGTH_SHORT).show()
                } else {
                    // Handle respons gagal
                }
            }

            override fun onFailure(call: Call<ResponseObject>, t: Throwable) {
                // Handle kegagalan koneksi atau request
            }
        })
    }

}
