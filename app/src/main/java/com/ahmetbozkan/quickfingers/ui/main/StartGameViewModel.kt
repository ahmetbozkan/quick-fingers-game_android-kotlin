package com.ahmetbozkan.quickfingers.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahmetbozkan.quickfingers.data.model.preference.GameMode
import com.ahmetbozkan.quickfingers.data.usecase.preference.GetGameModeFlowUseCase
import com.ahmetbozkan.quickfingers.data.usecase.preference.UpdateGameModeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StartGameViewModel @Inject constructor(
    private val updateGameModeUseCase: UpdateGameModeUseCase,
    getGameModeFlowUseCase: GetGameModeFlowUseCase
) : ViewModel() {

    val preferencesFlow = getGameModeFlowUseCase.invoke()

    fun onGameModeChanged(gameMode: GameMode) = viewModelScope.launch {
        updateGameModeUseCase.invoke(gameMode)
    }
}