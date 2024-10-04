package fr.eni.mod6demodatastore.repositories

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserPreferencesRepository(private val context : Context) {
    private val Context.datastore by preferencesDataStore(name = "user_preferences")

    private val KEY_PRIMARY_COLOR = stringPreferencesKey("primary_color")
    private val KEY_DARK_MODE = booleanPreferencesKey("dark_mode")

    suspend fun savePrimaryColor(color : String) {
        context.datastore.edit { preferences ->
            preferences[KEY_PRIMARY_COLOR] = color
        }
    }

    suspend fun saveDarkMode(enabled : Boolean) {
        context.datastore.edit { preferences ->
            preferences[KEY_DARK_MODE] = enabled
        }
    }

    val primaryColor : Flow<String?> = context.datastore.data.map {
        preferences ->
        preferences[KEY_PRIMARY_COLOR]
    }

    val darkMode : Flow<Boolean?> = context.datastore.data.map {
        preferences ->
        preferences[KEY_DARK_MODE]
    }

}