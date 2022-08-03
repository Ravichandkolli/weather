package com.ravichand.weatherapp.viewmodel

import android.widget.ProgressBar
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ravichand.weatherapp.network.RetroInstance
import com.ravichand.weatherapp.network.RetroService
import com.ravichand.weatherapp.network.WeatherResponseModel
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class MainActivityViewModel : ViewModel(){
     var weatherResponseModel: MutableLiveData<WeatherResponseModel> = MutableLiveData()

    fun getWeatherListObserver(): MutableLiveData<WeatherResponseModel>{
        return  weatherResponseModel
    }

    fun makeAPICall(query: String = "hyderabad", apiKey : String) {

        val  retroInstance = RetroInstance.getRetroInstance().create(RetroService::class.java)
        retroInstance.getWeatherFromAPI(query,apiKey)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(getWeatherListObserverRX())

    }

     private fun getWeatherListObserverRX(): Observer<WeatherResponseModel> {

        return object:Observer<WeatherResponseModel>{
            override fun onSubscribe(d: Disposable) {
                // start the progressBar
            }

            override fun onNext(t: WeatherResponseModel) {
                weatherResponseModel.postValue(t)
            }

            override fun onError(e: Throwable) {
                print("Error is $e")
               print(e.message)
            }

            override fun onComplete() {
              // close the progressBAR
            }


        }
    }
}

