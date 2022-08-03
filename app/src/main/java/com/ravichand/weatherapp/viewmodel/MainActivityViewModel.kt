package com.ravichand.weatherapp.viewmodel

import android.app.Activity
import android.content.Context
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ravichand.weatherapp.network.RetrofitServiceListener
import com.ravichand.weatherapp.network.Weather
import com.ravichand.weatherapp.network.WeatherResponseModel
import com.ravichand.weatherapp.repo.MainRepository
import com.ravichand.weatherapp.utils.ProgressUtils
import io.reactivex.Observable
import io.reactivex.internal.operators.observable.ObservableError

class MainActivityViewModel : ViewModel() , RetrofitServiceListener{
    var weatherResponseModel = ObservableField<WeatherResponseModel>()
    var weather : MutableLiveData<ArrayList<Weather>>
    var mainRepo : MainRepository? =null
    var location = ObservableField<String>()

    init {
        mainRepo = MainRepository(this as RetrofitServiceListener)
        weather = MutableLiveData()
        location.set("Hyderabad We111")
    }

    fun makeAPICall(context : Activity, s: String, s1: String) {
        ProgressUtils.showProgress(context)
       mainRepo?.makeAPICallFromRepo( s,s1)
    }

    override fun onResponse(response: Any?) {
        weatherResponseModel.set(response as WeatherResponseModel)
        ProgressUtils.hideProgress()
    }

    override fun onFailure(failure: Throwable?) {
        print("onFailure $failure")
        ProgressUtils.hideProgress()
    }




}

