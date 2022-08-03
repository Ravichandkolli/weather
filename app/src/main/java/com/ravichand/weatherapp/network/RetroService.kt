package com.ravichand.weatherapp.network

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Query


interface RetroService {

    @GET("weather")
    fun getWeatherFromAPI( @Query("q") city :String, @Query("appid") apiKey: String) : Observable<WeatherResponseModel>
}