package com.eniecole.mod7corrretrofit.service

import com.eniecole.mod7corrretrofit.bo.PonyResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path


interface PonyApiService {
    @GET("{city}/fr/free_bike_status.json")
    fun getBikeStatus(
        @Path("city") city: String = "Angers"
    ) : Call<PonyResponse>

}