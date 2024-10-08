package com.eniecole.mod7corrretrofit

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eniecole.mod7corrretrofit.bo.DataBikes
import com.eniecole.mod7corrretrofit.bo.PonyResponse
import com.eniecole.mod7corrretrofit.service.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


private const val TAG = "ListPonyVM"
class ListPonyVM : ViewModel(){
    private val _ponies = mutableStateOf<DataBikes?>(null)
    val ponies: State<DataBikes?> = _ponies
    init {
        viewModelScope.launch {
            fetchPonies()
        }
    }
    private suspend fun fetchPonies() {
        withContext(Dispatchers.IO) {
            RetrofitClient.ponyApiService.getBikeStatus(city = "Angers")
                .enqueue(object : Callback<PonyResponse> {
                override fun onResponse(call: Call<PonyResponse>,
                                        response: Response<PonyResponse>
                ) {
                    if (response.isSuccessful) {
                        // Utilisez les données ici
                        val body = response.body();
                        print("success: ${body}")
                        body?.data?.apply { bikes = bikes.filter {
                            it.vehicle_type_id == "scooter"
                        } }
                        _ponies.value = body?.data
                    } else {
                        _ponies.value = null
                    }
                }
                override fun onFailure(call: Call<PonyResponse>, t:Throwable){
                    Log.e(TAG, "onFailure: ", )
                    print(t.message)
                    // Gérez l'échec de l'appel
                }
            })
        }
    }
}