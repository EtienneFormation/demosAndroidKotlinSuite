package fr.eni.mod6demoroom.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import fr.eni.mod6demoroom.data.Music
import fr.eni.mod6demoroom.dbconfiguration.DatabaseClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MusicViewModel(val client : DatabaseClient) : ViewModel() {
    private val _musics = mutableStateOf<List<Music>>(emptyList())
    val musics : State<List<Music>> = _musics

    init {
        viewModelScope.launch {
            insertMusic(Music(
                0L, "Never gonna give you up", "Rick Astley", 220
            ))
            insertMusic(Music(
                0L, "Feel good", "Polo & Pan", 240
            ))

            loadMusics()
        }
    }

    suspend fun loadMusics() {
        withContext(Dispatchers.IO) {
            _musics.value = client.appDatabase.musicDao().getAllMusics()
        }
    }

    suspend fun insertMusic(music : Music) {
        withContext(Dispatchers.IO) {
            client.appDatabase.musicDao().insert(music)
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(
                modelClass: Class<T>,
                extras: CreationExtras
            ): T {
                val application = checkNotNull(extras[APPLICATION_KEY])
                return MusicViewModel(
                    DatabaseClient(application.applicationContext),
                ) as T
            }
        }
    }
}