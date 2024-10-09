package com.eniecole.mod8demoworkmanager

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import kotlinx.coroutines.delay

class SyncDBWorker(appCtcx: Context,
                   workerParams: WorkerParameters) :
    CoroutineWorker(appCtcx,workerParams) {
    override suspend fun doWork(): Result {
        delay(20000)
        return Result.success()
    }

}