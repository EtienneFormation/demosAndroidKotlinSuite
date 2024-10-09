package com.eniecole.mod8corrworkmanager.service

import com.eniecole.mod8corrworkmanager.bo.ResultIncidents
import retrofit2.Call
import retrofit2.http.GET

interface NaolibApiService {
    @GET("244400404_info-trafic-tan-temps-reel/records")
    fun getIncidents() : Call<ResultIncidents>
}