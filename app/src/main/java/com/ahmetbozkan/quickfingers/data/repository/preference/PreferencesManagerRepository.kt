package com.ahmetbozkan.quickfingers.data.repository.preference

import com.ahmetbozkan.quickfingers.data.model.preference.AppInfo
import com.ahmetbozkan.quickfingers.data.model.preference.GameMode
import kotlinx.coroutines.flow.Flow

interface PreferencesManagerRepository {

    /**
     * gameModes retrieved from datastore as Flow object
     */
    val gameModeFlow: Flow<GameMode>

    /**
     * app info's retrieved from datastore as Flow object
     */
    val appInfoFlow: Flow<AppInfo>

    /**
     * update game mode
     * @param mode to be used to update
     */
    suspend fun updateGameMode(mode: GameMode)

    /**
     * update words downloaded value of AppInfo store
     * @param downloaded to be used to update AppInfo
     */
    suspend fun updateWordsDownloaded(downloaded: Boolean)
}