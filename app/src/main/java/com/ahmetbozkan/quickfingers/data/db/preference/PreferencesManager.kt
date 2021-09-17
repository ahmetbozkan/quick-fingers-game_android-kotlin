package com.ahmetbozkan.quickfingers.data.db.preference

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.ahmetbozkan.quickfingers.data.model.preference.AppInfo
import com.ahmetbozkan.quickfingers.data.model.preference.GameMode
import com.ahmetbozkan.quickfingers.util.extension.appInfoDataStore
import com.ahmetbozkan.quickfingers.util.extension.gameModeDataStore
import com.ahmetbozkan.quickfingers.util.extension.handleException
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PreferencesManager @Inject constructor(@ApplicationContext context: Context) {

    private val gameModeStore: DataStore<Preferences> = context.gameModeDataStore

    private val appInfoStore: DataStore<Preferences> = context.appInfoDataStore

    val gameModePreferencesFlow = gameModeStore.handleException()
        .map { preferences ->
            val gameMode = GameMode.valueOf(
                preferences[PreferencesKeys.GAME_MODE] ?: GameMode.CLASSIC.name
            )
            gameMode
        }

    val appInfoFlow = appInfoStore.handleException()
        .map { preferences ->
            val wordsDownloaded = preferences[PreferencesKeys.WORDS_DOWNLOADED] ?: false

            AppInfo(wordsDownloaded = wordsDownloaded)
        }


    suspend fun updateGameMode(mode: GameMode) {
        gameModeStore.edit { preferences ->
            preferences[PreferencesKeys.GAME_MODE] = mode.name
        }
    }

    suspend fun updateWordsDownloaded(downloaded: Boolean) {
        appInfoStore.edit { preferences ->
            preferences[PreferencesKeys.WORDS_DOWNLOADED] = downloaded
        }
    }
}

private object PreferencesKeys {
    val GAME_MODE = stringPreferencesKey("game_mode")
    val WORDS_DOWNLOADED = booleanPreferencesKey("words_downloaded")
}