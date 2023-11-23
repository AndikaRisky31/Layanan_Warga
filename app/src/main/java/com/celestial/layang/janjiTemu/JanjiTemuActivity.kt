package com.celestial.layang.janjiTemu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.celestial.layang.databinding.ActivityJanjiTemuBinding

class JanjiTemuActivity : AppCompatActivity() {
    private lateinit var binding:ActivityJanjiTemuBinding
    private lateinit var janjitemuadapter:JanjiTemuAdapter
    private  lateinit var janjitemuViewModel:JanjiTemuViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityJanjiTemuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        janjitemuViewModel = ViewModelProvider(this)[JanjiTemuViewModel::class.java]


        val kontakList = janjitemuViewModel.getFasilitasList()


        val recyclerView = binding.listkontak


        janjitemuadapter = JanjiTemuAdapter(this,kontakList)


        recyclerView.adapter = janjitemuadapter
        recyclerView.layoutManager = LinearLayoutManager(this)

    }
}