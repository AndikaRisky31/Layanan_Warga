package com.celestial.layang.layanan.pengajuan.pengajuanidentity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.celestial.layang.R
import com.celestial.layang.databinding.ActivityPengajuanIdentityBinding
import com.celestial.layang.model.PengajuanData
import com.celestial.layang.profileedit.ProfileEditViewModelFactory
import com.celestial.layang.repository.PengajuanRepository
import com.celestial.layang.repository.UserDataRepository
import kotlinx.coroutines.launch

class PengajuanIdentityActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPengajuanIdentityBinding
    private lateinit var viewModel: PengajuanIdentityViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_pengajuan_identity)

        val judul = intent.getStringExtra("judul")
        binding.tvJenis.text = judul

        val userDataRepository: UserDataRepository by lazy {
            UserDataRepository(this)
        }
        val viewModelFactory = PengajuanIdentityViewModelFactory(PengajuanRepository())

        viewModel =
            ViewModelProvider(this, viewModelFactory)[PengajuanIdentityViewModel::class.java]

        binding.viewModel = viewModel
        viewModel.loadProfile(userDataRepository)

        viewModel.viewModelScope.launch {
            try {
                val data = userDataRepository.userData()

                binding.buttonNext.setOnClickListener {
                    viewModel.savePengajuanIdentity(
                        PengajuanData(
                            pengajuan_id = 1,
                            user_id = data.user_id,
                            nama = binding.etNama.toString(),
                            nik = binding.etNik.toString().toInt(),
                            ttl = binding.etTtl.toString(),
                            agama = binding.etAgama.toString(),
                            status = binding.etStatus.toString(),
                            alamat = binding.etAlamat.toString(),
                            jenis = binding.tvJenis.text.toString(),
                            imageKTP = "",
                            imageKK = ""
                        )
                    )
                    navigateNext()
                }
            } catch (e: Exception) {
                // Handle exceptions, show error message, log, etc.
                Log.e("Error", "Error fetching profile data: ${e.message}", e)
            }
        }


        val buttonKembali = findViewById<ImageButton>(R.id.button_back)
        buttonKembali.setOnClickListener {
            finish()
        }

        val buttonSelanjutnya = findViewById<Button>(R.id.button_next)
        buttonSelanjutnya.setOnClickListener {
            intent = Intent(this, PengajuanBerkasActivity::class.java)
            startActivity(intent)
        }
    }

    private fun navigateNext() {
        val intent = Intent(this, PengajuanBerkasActivity::class.java)
        intent.putExtra("navigateToPengajuanBerkas", true)
        startActivity(intent)
        finish()
    }
}