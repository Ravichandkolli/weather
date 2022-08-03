package com.ravichand.weatherapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.ravichand.weatherapp.viewmodel.MainActivityViewModel

class MainActivity : AppCompatActivity() {
    lateinit var  viewModel: MainActivityViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        viewModel.makeAPICall("hyderabad","2fa79cf44d4319c653c8d1b82d325e58")
    }
}