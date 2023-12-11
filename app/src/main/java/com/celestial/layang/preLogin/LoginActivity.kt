package com.celestial.layang.preLogin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.celestial.layang.api.ApiClient
import com.celestial.layang.api.ApiService
import com.celestial.layang.databinding.ActivityLoginBinding
import com.celestial.layang.home.MenuActivity
import com.celestial.layang.model.LoginRequest
import com.celestial.layang.model.LoginResponse
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var apiService:ApiService
    private lateinit var intent: Intent
    override fun onCreate(savedInstanceState: Bundle?) {
        apiService = ApiClient.apiService
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //event listener teks lupa password
        binding.txtlupapass.setOnClickListener{
            intent = Intent(this,ForgotPasswordActivity::class.java)
            startActivity(intent)
        }
        //event listener teks mendaftar
        binding.linkMendaftar.setOnClickListener{
            intent = Intent(this,RegisterActivity::class.java)
            startActivity(intent)
        }
        //event listener button login
        binding.buttonMasuk.setOnClickListener {
            loginUser(binding.inputUsername.text.toString(),binding.password.text.toString())
        }
    }
    private fun loginUser(username: String, password: String) {
        val loginRequest = LoginRequest(username = username, userPassword = password)

        val call = apiService.login(loginRequest)
        call.enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (response.isSuccessful) {
                    // TODO:  menambah enkrisi pada data
                    val loginResponse = response.body()
                    intent = Intent(this@LoginActivity,MenuActivity::class.java)
                    startActivity(intent)
                    Toast.makeText(this@LoginActivity, "Login Berhasil ${loginResponse?.userId}", Toast.LENGTH_SHORT).show()
                } else {
                    Log.e("Loginnnn", "HTTP Status Code: ${response.code()}")
                    Toast.makeText(this@LoginActivity, "$username dan $password", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Toast.makeText(this@LoginActivity, "Tidak terhubung", Toast.LENGTH_SHORT).show()
                Log.e("LoginActivity", "Gagal melakukan permintaan login", t)
            }
        })
    }
}