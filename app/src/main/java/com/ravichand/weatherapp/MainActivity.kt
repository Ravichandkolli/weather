package com.ravichand.weatherapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.ravichand.weatherapp.databinding.ActivityMainBinding
import com.ravichand.weatherapp.viewmodel.MainActivityViewModel

class MainActivity : AppCompatActivity() {
    lateinit var viewModel: MainActivityViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var binding =
            DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        viewModel.makeAPICall(
            this@MainActivity,
            resources.getString(R.string.location),
            resources.getString(R.string.api_key)
        )
        binding.viewModel = viewModel
    }
}