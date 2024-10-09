package com.eniecole.mod8corrworkmanager.worker

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.Data
import androidx.work.WorkerParameters
import com.eniecole.mod8corrworkmanager.service.RetrofitClient
import com.google.gson.Gson

class DownloadIncidentsWorker(appCtcx: Context,
                   workerParams: WorkerParameters
) :
    CoroutineWorker(appCtcx,workerParams) {
    override suspend fun doWork(): Result {
        //Récupération des données de l'APIx
        val res = RetrofitClient.naolibApiService.getIncidents().execute()
        if(res.isSuccessful && res.body()!=null){
            return Result.success(Data
                .Builder()
                .putString(
                    "incidents",
                    Gson().toJson(res.body()) )
                .build()
            );
        }
        return Result.failure()
    }

}