package fr.eni.mod6demodatastore.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import fr.eni.mod6demodatastore.repositories.UserPreferencesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class SettingsViewModel(private val userPreferencesRepository: UserPreferencesRepository)
    : ViewModel() {
    val primaryColor : Flow<String?> = userPreferencesRepository.primaryColor
    val darkModeEnabled : Flow<Boolean?> = userPreferencesRepository.darkMode

    fun savePrimaryColor(color : String) {
        viewModelScope.launch {
            userPreferencesRepository.savePrimaryColor(color)
        }
    }

    fun saveDarkMode(enabled : Boolean) {
        viewModelScope.launch {
            userPreferencesRepository.saveDarkMode(enabled)
        }
    }

    /*
        Une classe interne utilisée pour expliquer au système Android
        comment il est censé injecter l'instance de BookDBHelper à notre
        viewmodel
     */
    companion object {
        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(
                modelClass: Class<T>,
                extras: CreationExtras
            ): T {
                val application = checkNotNull(extras[APPLICATION_KEY])
                return SettingsViewModel(
                    UserPreferencesRepository(application.applicationContext),
                ) as T
            }
        }
    }
}