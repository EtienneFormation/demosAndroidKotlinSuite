package com.eniecole.mod8demoworkmanager

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkInfo
import androidx.work.WorkManager
import kotlinx.coroutines.launch

class ListThingsViewModel(val workManager: WorkManager): ViewModel() {
    private val _resultDbSync= mutableStateOf<Boolean?>(null)
    val resultDbSync: State<Boolean?> = _resultDbSync

    init {
        viewModelScope.launch {
            sync()
        }
    }
    suspend fun sync(){
        _resultDbSync.value = null;
        val constraintsWifiStorage=Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .setRequiredNetworkType(NetworkType.UNMETERED)
            .setRequiresBatteryNotLow(true)
            .build()
        val syncDbRequest = OneTimeWorkRequestBuilder<SyncDBWorker>()
                .setConstraints(constraintsWifiStorage)
                .build()
        workManager.enqueue(syncDbRequest)
        workManager.getWorkInfoByIdLiveData(syncDbRequest.id).asFlow().collect {
            when (it.state) {
                WorkInfo.State.SUCCEEDED -> _resultDbSync.value = true
                WorkInfo.State.FAILED -> _resultDbSync.value = false
                else -> _resultDbSync.value = null
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
                return ListThingsViewModel(
                    WorkManager.getInstance(application.applicationContext)
                ) as T
            }
        }
    }

}