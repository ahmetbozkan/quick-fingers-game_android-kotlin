package com.ahmetbozkan.quickfingers.data.repository.preference

import com.ahmetbozkan.quickfingers.data.db.preference.GameMode
import com.ahmetbozkan.quickfingers.data.db.preference.PreferencesManager
import javax.inject.Inject

class PreferencesManagerRepositoryImpl @Inject constructor(
    private val manager: PreferencesManager
) : PreferencesManagerRepository {

    override val preferencesFlow = manager.preferencesFlow

    override suspend fun updateGameMode(mode: GameMode) {
        manager.updateGameMode(mode)
    }
}