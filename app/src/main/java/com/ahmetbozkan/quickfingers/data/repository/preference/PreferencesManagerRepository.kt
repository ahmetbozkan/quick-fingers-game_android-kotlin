package com.ahmetbozkan.quickfingers.data.repository.preference

import com.ahmetbozkan.quickfingers.data.db.preference.GameMode
import kotlinx.coroutines.flow.Flow

interface PreferencesManagerRepository {

    /**
     * gameModes retrieved from datastore as Flow object
     */
    val preferencesFlow: Flow<GameMode>

    /**
     * update game mode
     * @param mode to be used to update
     */
    suspend fun updateGameMode(mode: GameMode)
}