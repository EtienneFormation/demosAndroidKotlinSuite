package com.eniecole.mod8corrworkmanager.vm

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkInfo
import androidx.work.WorkManager
import com.eniecole.mod8corrworkmanager.bo.ResultIncidents
import com.eniecole.mod8corrworkmanager.worker.DownloadIncidentsWorker
import com.google.gson.Gson
import kotlinx.coroutines.launch

private const val TAG = "ListIncidentsVM"
class ListIncidentsVM(val workManager: WorkManager) : ViewModel() {
    private val _resultIncidents= mutableStateOf<ResultIncidents?>(null)
    val resultIncidents: State<ResultIncidents?> = _resultIncidents

    init {
        viewModelScope.launch {
            downloadIncidents()
        }
    }
    suspend fun downloadIncidents(){
        val syncDbRequest = OneTimeWorkRequestBuilder<DownloadIncidentsWorker>()
            .build()
        workManager.enqueue(syncDbRequest)
        workManager.getWorkInfoByIdLiveData(syncDbRequest.id).asFlow().collect {
            when (it.state) {
                WorkInfo.State.SUCCEEDED -> {
                    //Log.i(TAG, "downloadIncidents: ${it.outputData.getString("incidents")}")
                    _resultIncidents.value  =
                        it.outputData.getString("incidents")
                            ?.let { it1 ->
                                Gson().fromJson(it1,ResultIncidents::class.java)
                            }

                }
                WorkInfo.State.FAILED -> Log.e(TAG, "downloadIncidents: ERROR FAILED", )
                else -> Log.i(TAG, "downloadIncidents: Autre ")
            }
        }
    }


    companion object {
        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(
                modelClass: Class<T>,
                extras: CreationExtras
            ): T {
                val application = checkNotNull(extras[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY])
                return ListIncidentsVM(
                    WorkManager.getInstance(application.applicationContext)
                ) as T
            }
        }
    }
}