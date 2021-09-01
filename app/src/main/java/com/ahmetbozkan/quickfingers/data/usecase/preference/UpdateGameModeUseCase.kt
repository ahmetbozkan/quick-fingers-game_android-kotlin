package com.ahmetbozkan.quickfingers.data.usecase.preference

import com.ahmetbozkan.quickfingers.data.db.preference.GameMode
import com.ahmetbozkan.quickfingers.data.repository.preference.PreferencesManagerRepository
import javax.inject.Inject

class UpdateGameModeUseCase @Inject constructor(
    private val repository: PreferencesManagerRepository
) {

    suspend operator fun invoke(gameMode: GameMode) =
        repository.updateGameMode(gameMode)
}