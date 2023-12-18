package com.celestial.layang.register

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.celestial.layang.R
import com.celestial.layang.api.ApiClient
import com.celestial.layang.api.ApiService
import com.celestial.layang.databinding.ActivityRegister2Binding
import com.celestial.layang.model.DataKabupaten
import com.celestial.layang.model.DataKecamatan
import com.celestial.layang.model.DataKelurahan
import com.celestial.layang.model.DataProvinsi
import com.celestial.layang.model.RegisterResponse
import com.celestial.layang.model.UserData
import com.celestial.layang.preLogin.LoginActivity
import com.celestial.layang.repository.DaerahRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Register2Activity : AppCompatActivity() {
    private lateinit var binding: ActivityRegister2Binding
    private lateinit var apiService: ApiService
    private val daerahRepository = DaerahRepository()
    private var isSpinnerProvinsiTouched = false
    private var isSpinnerKabupatenTouched = false
    private var isSpinnerKecamatanTouched = false
    private var isSpinnerKelurahanTouched = false
    private lateinit var provinces:List<DataProvinsi>
    private lateinit var regencies:List<DataKabupaten>
    private lateinit var districts:List<DataKecamatan>
    private lateinit var villages:List<DataKelurahan>
    private lateinit var selectedProvince:DataProvinsi
    private lateinit var selectedRegency:DataKabupaten
    private lateinit var selectedDistrict:DataKecamatan
    private lateinit var selectedVillage:DataKelurahan

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegister2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        apiService = ApiClient.apiService

        getProvinces()
        setupSpinnerProvinsiListener()
        setupSpinnerKabupatenListener()
        setupSpinnerKecamatanListener()
        setupSpinnerKelurahanListener()
        val email = intent.getStringExtra("email")
        val password = intent.getStringExtra("password")

        binding.btnRegister.setOnClickListener{
            createUser(email!!,password!!)
        }

    }
    private fun setupSpinner(spinner: Spinner, data: List<String>) {
        val adapter = ArrayAdapter(this, R.layout.spinner, data)
        adapter.setDropDownViewResource(R.layout.item_spinner)
        spinner.adapter = adapter
    }

    private fun getProvinces() {
        daerahRepository.getProvinces().enqueue(object : Callback<List<DataProvinsi>> {
            override fun onResponse(
                call: Call<List<DataProvinsi>>,
                response: Response<List<DataProvinsi>>
            ) {
                if (response.isSuccessful) {
                    provinces = response.body()!!
                    provinces.let {
                        val provinceNames = mutableListOf("Pilih Provinsi")
                        provinceNames.addAll(provinces.map { provinsi -> provinsi.name })
                        setupSpinner(binding.spinnerProvinsi, provinceNames)
                    }
                } else {
                    // Handle kegagalan response
                }
            }

            override fun onFailure(call: Call<List<DataProvinsi>>, t: Throwable) {
                // Handle kegagalan request
            }
        })
    }

    private fun getRegencies(provinceId: String) {
        daerahRepository.getRegencies(provinceId).enqueue(object : Callback<List<DataKabupaten>> {
            override fun onResponse(
                call: Call<List<DataKabupaten>>,
                response: Response<List<DataKabupaten>>
            ) {
                if (response.isSuccessful) {
                    regencies = response.body()!!
                    regencies.let {
                        // Memasukkan data kabupaten ke dalam Spinner Kabupaten
                        val regencyNames = mutableListOf("Pilih Kota Kabupaten")
                        regencyNames.addAll(regencies.map { kabupaten -> kabupaten.name })
                        setupSpinner(binding.spinnerKabupaten, regencyNames)
                    }
                } else {
                    // Handle kegagalan response
                }
            }

            override fun onFailure(call: Call<List<DataKabupaten>>, t: Throwable) {
                // Handle kegagalan request
            }
        })
    }
    private fun getDistricts(regencyId: String) {
        daerahRepository.getDistricts(regencyId).enqueue(object : Callback<List<DataKecamatan>> {
            override fun onResponse(
                call: Call<List<DataKecamatan>>,
                response: Response<List<DataKecamatan>>
            ) {
                if (response.isSuccessful) {
                    districts = response.body()!!
                    districts.let {
                        // Memasukkan data kecamatan ke dalam Spinner Kecamatan
                        val districtNames = mutableListOf("Pilih Kecamatan")
                        districtNames.addAll(districts.map { kecamatan -> kecamatan.name })

                        setupSpinner(binding.spinnerKecamatan, districtNames)
                    }
                } else {
                    // Handle kegagalan response
                }
            }

            override fun onFailure(call: Call<List<DataKecamatan>>, t: Throwable) {
                // Handle kegagalan request
            }
        })
    }

    private fun getVillages(districtId: String) {
        daerahRepository.getVillages(districtId).enqueue(object : Callback<List<DataKelurahan>> {
            override fun onResponse(
                call: Call<List<DataKelurahan>>,
                response: Response<List<DataKelurahan>>
            ) {
                if (response.isSuccessful) {
                    villages = response.body()!!
                    villages.let {
                        // Memasukkan data kelurahan ke dalam Spinner Kelurahan
                        val villageNames = mutableListOf("Pilih Kelurahan")
                        villageNames.addAll(villages.map { kelurahan -> kelurahan.name })
                        setupSpinner(binding.spinnerKelurahan, villageNames)
                    }
                } else {
                    Log.e("kellll","konyokkk")
                }
            }

            override fun onFailure(call: Call<List<DataKelurahan>>, t: Throwable) {
                // Handle kegagalan request
            }
        })
    }



    @SuppressLint("ClickableViewAccessibility")
    private fun setupSpinnerProvinsiListener() {
        binding.spinnerProvinsi.setOnTouchListener { _, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                isSpinnerProvinsiTouched = true
            }
            false
        }

        binding.spinnerProvinsi.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (isSpinnerProvinsiTouched) {
                    selectedProvince = provinces[position-1]
                    getRegencies(selectedProvince.id)
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun setupSpinnerKabupatenListener() {
        binding.spinnerKabupaten.setOnTouchListener { _, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                isSpinnerKabupatenTouched = true
            }
            false
        }

        binding.spinnerKabupaten.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (isSpinnerKabupatenTouched) {
                    if (position == 0) {
                        districts = emptyList()
                        villages = emptyList()
                    } else if (position <= regencies.size) {
                        // Lanjutkan dengan operasi normal saat memilih item selain pertama
                        selectedRegency = regencies[position - 1]
                        getDistricts(selectedRegency.id)
                    }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Tidak ada yang harus dilakukan saat tidak ada yang dipilih
            }
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun setupSpinnerKecamatanListener() {
        binding.spinnerKecamatan.setOnTouchListener { _, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                isSpinnerKecamatanTouched = true
            }
            false
        }

        binding.spinnerKecamatan.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (isSpinnerKecamatanTouched) {
                    if (position == 0) {
                        villages = emptyList()
                    } else if (position <= districts.size) {
                        // Lanjutkan dengan operasi normal saat memilih item selain pertama
                        selectedDistrict = districts[position - 1]
                        getVillages(selectedDistrict.id)
                    }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Tidak ada yang harus dilakukan saat tidak ada yang dipilih
            }
        }
    }



    @SuppressLint("ClickableViewAccessibility")
    private fun setupSpinnerKelurahanListener() {
        binding.spinnerKelurahan.setOnTouchListener { _, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                isSpinnerKelurahanTouched = true
            }
            false
        }

        binding.spinnerKelurahan.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (isSpinnerKelurahanTouched) {
                    if (position == 0) {
                        villages = emptyList()
                    } else if (position <= villages.size) {
                        // Lanjutkan dengan operasi normal saat memilih item selain pertama
                        selectedVillage = villages[position - 1]
                        Log.e("data yang diambil", selectedVillage.toString())
                    }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Tidak ada yang harus dilakukan saat tidak ada yang dipilih
            }
        }
    }


    private fun createUser(email: String, password: String) {
        val username = binding.inputNamaLengkap.text.toString().trim()
        val nomor = binding.inputNomor.text.toString().trim()
        val alamat = binding.inputAlamat.text.toString().trim()

        if (username.isEmpty() || nomor.isEmpty() || alamat.isEmpty()) {
            showToast("Tolong Isi Semua Data")
            return
        }

        val inuserData = UserData(
            user_id = null,
            kelurahan_id = selectedVillage.id.toInt(),
            email = email,
            password = password,
            username = username,
            nomor = nomor,
            alamat = alamat,
            kota = getKota(),
            imageURL = "fsdgh"
        )

        apiService.addUser(inuserData).enqueue(object : Callback<RegisterResponse> {
            override fun onResponse(call: Call<RegisterResponse>, response: Response<RegisterResponse>) {
                if (response.isSuccessful) {
                    val registerResponse = response.body()

                    if (registerResponse?.success == true) {
                        // Handle user creation success
                        val userData = registerResponse.data
                        showToast("Silahkan login")
                        val intent = Intent(this@Register2Activity, LoginActivity::class.java)
                        startActivity(intent)
                    } else {
                        // Handle user creation failure
                        showToast("User creation failed: ${registerResponse?.message}")
                    }
                } else {
                    // Handle response gagal di sini
                    showToast("Failed to create user. Please try again.")
                }
            }

            override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                // Handle kegagalan request di sini
            }
        })
    }


    private fun getKota():String{
        val kelurahan = selectedVillage.name
        val kecamatan = selectedDistrict.name
        val kabupaten = selectedRegency.name
        val provinsi =selectedProvince.name
        return "$kelurahan,$kecamatan,$kabupaten. $provinsi"
    }
    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
