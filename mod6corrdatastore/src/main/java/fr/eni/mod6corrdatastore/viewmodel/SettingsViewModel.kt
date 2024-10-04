package fr.eni.mod6corrdatastore.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import fr.eni.mod6corrdatastore.repositories.SettingsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class SettingsViewModel(private val userPreferencesRepository: SettingsRepository)
    : ViewModel() {
    val personalAddress : Flow<String?> = userPreferencesRepository.personalAddress
    val workAddress : Flow<String?> = userPreferencesRepository.workAddress
    val company : Flow<String?> = userPreferencesRepository.company
    val reductionCode : Flow<Int?> = userPreferencesRepository.reductionCode

    fun savePersonalAddress(addr : String) {
        viewModelScope.launch {
            userPreferencesRepository.savePersonalAddress(addr)
        }
    }

    fun saveWorkAddress(addr : String) {
        viewModelScope.launch {
            userPreferencesRepository.saveWorkAddress(addr)
        }
    }

    fun saveCompany(company : String) {
        viewModelScope.launch {
            userPreferencesRepository.saveCompany(company)
        }
    }

    fun saveReductionCode(code : Int) {
        viewModelScope.launch {
            userPreferencesRepository.saveReductionCode(code)
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
                    SettingsRepository(application.applicationContext),
                ) as T
            }
        }
    }
}