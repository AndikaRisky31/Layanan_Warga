// YourActivity.kt
package com.celestial.layang.register

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.celestial.layang.api.ApiClient
import com.celestial.layang.api.ApiService
import com.celestial.layang.databinding.ActivityRegister2Binding
import com.celestial.layang.databinding.ActivityRegisterBinding
import com.celestial.layang.model.UserData
import com.celestial.layang.preLogin.LoginActivity


class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var apiService: ApiService

    override fun onCreate(savedInstanceState: Bundle?) {
        apiService = ApiClient.apiService
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.linklogin.setOnClickListener{
            intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
        binding.buttonMendaftar.setOnClickListener{
            validateAndNavigate()
        }
    }
    // Fungsi untuk validasi data
    private fun validateAndNavigate() {
        val email = binding.inputEmail.text.toString()
        val password = binding.password.text.toString()
        val password2 = binding.password2.text.toString()

        // Validasi email tidak boleh kosong dan format email valid
        if (email.isEmpty() || !isValidEmail(email)) {
            showError("Email tidak valid")
            return
        }

        // Validasi kedua password sesuai dan tidak boleh kosong
        if (password.isEmpty() || password != password2) {
            showError("Password tidak cocok")
            return
        }
        navigateToRegisterActivity2(email,password)
    }


    private fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    // Fungsi untuk navigasi ke RegisterActivity2
    private fun navigateToRegisterActivity2(email:String,password:String) {
        val intent = Intent(this, Register2Activity::class.java)
        intent.putExtra("email", email)
        intent.putExtra("password",password)
        startActivity(intent)
    }
    private fun isValidEmail(email: CharSequence): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}
