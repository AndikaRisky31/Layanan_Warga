package com.celestial.layang.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.databinding.DataBindingUtil
import com.celestial.layang.R
import com.celestial.layang.databinding.FragmentProfileBinding
import com.celestial.layang.repository.ProfileRepository

class ProfileFragment : Fragment() {

    private lateinit var profileviewmodel: ProfileViewModel
    private lateinit var binding: FragmentProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflating the layout with Data Binding
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile, container, false)

        // Create an instance of ProfileRepository (you might need to pass necessary parameters)
        val profileRepository: ProfileRepository by lazy {
            ProfileRepository(requireContext())
        } // You need to provide a valid implementation
        val viewModelFactory = ProfileViewModelFactory(profileRepository)
        profileviewmodel = ViewModelProvider(this, viewModelFactory)[ProfileViewModel::class.java]

        // Bind the ViewModel to the layout
        binding.viewModel = profileviewmodel
        binding.lifecycleOwner = this

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        profileviewmodel.loadProfile()
    }
}
