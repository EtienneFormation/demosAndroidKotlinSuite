package com.eniecole.mod8demoworkmanager

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.await
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
        _resultDbSync.value = null
        val syncDbRequest = OneTimeWorkRequestBuilder<SyncDBWorker>().build()
        val operation = workManager.beginWith(syncDbRequest).enqueue().await()
        _resultDbSync.value = true



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