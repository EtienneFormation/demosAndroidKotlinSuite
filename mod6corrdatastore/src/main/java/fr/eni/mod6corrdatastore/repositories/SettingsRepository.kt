package fr.eni.mod6corrdatastore.repositories

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SettingsRepository(private val context : Context) {
    private val Context.datastore by preferencesDataStore(name = "user_entries")

    private val KEY_ADDR1 = stringPreferencesKey("addr1")
    private val KEY_ADDR2 = stringPreferencesKey("addr2")
    private val KEY_COMPANY = stringPreferencesKey("company")
    private val KEY_REDUC = intPreferencesKey("reduc")

    suspend fun savePersonalAddress(addr : String) {
        context.datastore.edit { preferences ->
            preferences[KEY_ADDR1] = addr
        }
    }

    suspend fun saveWorkAddress(addr : String) {
        context.datastore.edit { preferences ->
            preferences[KEY_ADDR2] = addr
        }
    }

    suspend fun saveCompany(company : String) {
        context.datastore.edit { preferences ->
            preferences[KEY_COMPANY] = company
        }
    }

    suspend fun saveReductionCode(reductionCode : Int) {
        context.datastore.edit { preferences ->
            preferences[KEY_REDUC] = reductionCode
        }
    }

    val personalAddress : Flow<String?> = context.datastore.data.map {
            preferences ->
        preferences[KEY_ADDR1]
    }

    val workAddress : Flow<String?> = context.datastore.data.map {
            preferences ->
        preferences[KEY_ADDR2]
    }

    val company : Flow<String?> = context.datastore.data.map {
            preferences ->
        preferences[KEY_COMPANY]
    }

    val reductionCode : Flow<Int?> = context.datastore.data.map {
            preferences ->
        preferences[KEY_REDUC]
    }
}