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

class MainActivityViewModel : ViewModel(), RetrofitServiceListener {
    var weatherResponseModel = ObservableField<WeatherResponseModel>()
    var weather = ObservableField<Weather>()
    var mainRepo: MainRepository? = null
    var location = ObservableField<String>()

    init {
        mainRepo = MainRepository(this as RetrofitServiceListener)
    }

    fun makeAPICall(context: Activity, city: String, api_key: String) {
        location.set(city)
        ProgressUtils.showProgress(context)
        mainRepo?.makeAPICallFromRepo(city, api_key)
    }

    override fun onResponse(response: Any?) {
        weatherResponseModel.set(response as WeatherResponseModel)
        weather.set(
            weatherResponseModel.get()?.weather?.get(0) ?: Weather(
                1,
                "default",
                "description",
                "123"
            )
        )
        ProgressUtils.hideProgress()
    }

    override fun onFailure(failure: Throwable?) {
        print("onFailure $failure")
        ProgressUtils.hideProgress()
    }
}

