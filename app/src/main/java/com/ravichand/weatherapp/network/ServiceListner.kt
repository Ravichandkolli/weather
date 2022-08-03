package com.ravichand.weatherapp.network

interface RetrofitServiceListener {

    fun onResponse(response : Any?)

    fun onFailure(failure : Throwable?)

}