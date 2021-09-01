package com.ahmetbozkan.quickfingers.data.usecase.preference

import com.ahmetbozkan.quickfingers.data.db.preference.GameMode
import com.ahmetbozkan.quickfingers.data.repository.preference.PreferencesManagerRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPreferencesFlowUseCase @Inject constructor(
    private val repository: PreferencesManagerRepository
) {

    operator fun invoke(): Flow<GameMode> = repository.preferencesFlow
}