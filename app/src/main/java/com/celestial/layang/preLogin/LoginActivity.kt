package com.celestial.layang.preLogin

import android.content.Context
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
import com.celestial.layang.register.RegisterActivity
import com.celestial.layang.repository.UserPreferences
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var apiService: ApiService
    private lateinit var intent: Intent

    // Instantiate com.celestial.layang.repository.UserPreferences
    private val userPreferences: UserPreferences by lazy {
        UserPreferences(getSharedPreferences("User_Data", Context.MODE_PRIVATE))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        apiService = ApiClient.apiService
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Event listener teks lupa password
        binding.txtlupapass.setOnClickListener {
            intent = Intent(this, ForgotPasswordActivity::class.java)
            startActivity(intent)
        }

        // Event listener teks mendaftar
        binding.linkMendaftar.setOnClickListener {
            intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        // Event listener button login
        binding.buttonMasuk.setOnClickListener {
            intent = Intent(this@LoginActivity, MenuActivity::class.java)
            startActivity(intent)
            //loginUser(binding.inputUsername.text.toString(), binding.password.text.toString())
        }
    }

    private fun loginUser(email: String, password: String) {
        val loginRequest = LoginRequest(email, password)

        val call = apiService.login(loginRequest)
        call.enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (response.isSuccessful) {
                    val loginResponse = response.body()
                    // Save user data using userPreferences instance
                    userPreferences.saveUserData(loginResponse!!.data)
                    intent = Intent(this@LoginActivity, MenuActivity::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(this@LoginActivity, "$email dan $password", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Toast.makeText(this@LoginActivity, "Tidak terhubung", Toast.LENGTH_SHORT).show()
                Log.e("LoginActivity", "Gagal melakukan permintaan login", t)
            }
        })
    }
}
