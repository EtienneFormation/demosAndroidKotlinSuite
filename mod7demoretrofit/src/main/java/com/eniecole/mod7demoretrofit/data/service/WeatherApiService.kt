package com.eniecole.mod7demoretrofit.data.service

import com.eniecole.mod7demoretrofit.data.bo.WeatherResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiService {
    @GET("v1/forecast")
    fun getWeatherData(
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double,
        @Query("current") current: String,
        @Query("hourly") hourly: String = "temperature_2m"
    ) : Call<WeatherResponse>
}