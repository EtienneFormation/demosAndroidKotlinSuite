package com.eniecole.mod7demoretrofit.vm

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eniecole.mod7demoretrofit.data.bo.HourlyData
import com.eniecole.mod7demoretrofit.data.bo.WeatherResponse
import com.eniecole.mod7demoretrofit.data.service.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListWeatherVM: ViewModel() {
    private val _weather = mutableStateOf<HourlyData?>(null)
    val weather: State<HourlyData?> = _weather
    init {
        viewModelScope.launch {
            fetchWeather()
        }
    }
    private suspend fun fetchWeather() {
        withContext(Dispatchers.IO) {
            RetrofitClient.weatherApiService.getWeatherData(
                latitude = 47.224152,
                longitude = -1.6322685,
                current = "temperature_2m,wind_speed_10m",
                hourly = "temperature_2m"
            ).enqueue(object : Callback<WeatherResponse> {
                override fun onResponse(call: Call<WeatherResponse>,
                                        response: Response<WeatherResponse>
                ) {
                    if (response.isSuccessful) {
                        // Utilisez les données ici
                        val body = response.body();
                        print("success: ${body}")
                        _weather.value = body?.hourly
                    } else {
                        _weather.value = null
                    }
                }
                override fun onFailure(call: Call<WeatherResponse>, t:Throwable){
                    print(t.message)
                    // Gérez l'échec de l'appel
                }
            }
        )
    }
    }
}