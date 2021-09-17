package com.ahmetbozkan.quickfingers.data.repository.preference

import com.ahmetbozkan.quickfingers.data.db.preference.PreferencesManager
import com.ahmetbozkan.quickfingers.data.model.preference.AppInfo
import com.ahmetbozkan.quickfingers.data.model.preference.GameMode
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PreferencesManagerRepositoryImpl @Inject constructor(
    private val manager: PreferencesManager
) : PreferencesManagerRepository {

    override val gameModeFlow = manager.gameModePreferencesFlow

    override val appInfoFlow: Flow<AppInfo> = manager.appInfoFlow

    override suspend fun updateGameMode(mode: GameMode) {
        manager.updateGameMode(mode)
    }

    override suspend fun updateWordsDownloaded(downloaded: Boolean) {
        manager.updateWordsDownloaded(downloaded)
    }


}