package fr.eni.filrouge.data.worker

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.Data
import androidx.work.WorkerParameters
import fr.eni.filrouge.data.repository.ProductRepository
import fr.eni.filrouge.data.repository.SyncStatus

class SyncDataWorker (appCtcx: Context,
                       workerParams: WorkerParameters
    ) : CoroutineWorker(appCtcx,workerParams) {
    override suspend fun doWork(): Result {
        val status = ProductRepository(applicationContext).syncApiDB()
         return when(status){
            SyncStatus.success -> Result.success()
            SyncStatus.fail -> Result.failure(Data.Builder().putString("error",
                "Erreur lors de la synchronisation").build())
            SyncStatus.empty_internet_response ->  Result.failure(Data.Builder().putString("error",
                "L'API distante ne renvoie pas de donnée").build())
        }
    }


}