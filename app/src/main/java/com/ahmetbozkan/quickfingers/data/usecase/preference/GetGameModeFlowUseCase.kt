package com.ahmetbozkan.quickfingers.data.usecase.preference

import com.ahmetbozkan.quickfingers.data.model.preference.GameMode
import com.ahmetbozkan.quickfingers.data.repository.preference.PreferencesManagerRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetGameModeFlowUseCase @Inject constructor(
    private val repository: PreferencesManagerRepository
) {

    operator fun invoke(): Flow<GameMode> = repository.gameModeFlow
}