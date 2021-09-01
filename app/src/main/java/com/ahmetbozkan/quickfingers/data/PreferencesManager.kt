package com.ahmetbozkan.quickfingers.data

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import com.ahmetbozkan.quickfingers.util.extension.dataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

private const val TAG = "PreferencesManager"

enum class GameMode { CLASSIC, ARCADE, PARAGRAPH }

@Singleton
class PreferencesManager @Inject constructor(@ApplicationContext context: Context) {

    private val dataStore: DataStore<Preferences> = context.dataStore

    val preferencesFlow = dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                Log.e(TAG, "Error reading preferences.", exception)
                emit(emptyPreferences())
            } else {
                throw exception
            }

        }
        .map { preferences ->
            val gameMode = GameMode.valueOf(
                preferences[PreferencesKeys.GAME_MODE] ?: GameMode.CLASSIC.name
            )
            gameMode
        }

    suspend fun updateGameMode(mode: GameMode) {
        dataStore.edit { preferences ->
            preferences[PreferencesKeys.GAME_MODE] = mode.name
        }
    }

    private object PreferencesKeys {
        val GAME_MODE = stringPreferencesKey("game_mode")
    }
}