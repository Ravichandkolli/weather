package com.ravichand.weatherapp.repo
import com.ravichand.weatherapp.network.RetroInstance
import com.ravichand.weatherapp.network.RetroService
import com.ravichand.weatherapp.network.RetrofitServiceListener
import com.ravichand.weatherapp.network.WeatherResponseModel
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class MainRepository() {

    var retrofitServiceListener : RetrofitServiceListener? = null

    constructor(context: RetrofitServiceListener) : this(){
        this.retrofitServiceListener = context
    }

    fun makeAPICallFromRepo(query: String = "hyderabad", apiKey : String) {

        val  retroInstance = RetroInstance.getRetroInstance().create(RetroService::class.java)
        retroInstance.getWeatherFromAPI(query,apiKey)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(getWeatherListObserverRX())

    }

    private fun getWeatherListObserverRX(): Observer<WeatherResponseModel> {

        return object: Observer<WeatherResponseModel> {
            override fun onSubscribe(d: Disposable) {
                // start the progressBar
            }

            override fun onNext(t: WeatherResponseModel) {

               retrofitServiceListener!!.onResponse(t)
            }

            override fun onError(e: Throwable) {
                retrofitServiceListener!!.onFailure(e)
            }

            override fun onComplete() {
                // close the progressBAR
            }


        }
    }
}