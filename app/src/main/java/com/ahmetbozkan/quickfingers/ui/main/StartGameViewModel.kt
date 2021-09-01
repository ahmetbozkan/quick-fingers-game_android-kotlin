package com.ahmetbozkan.quickfingers.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahmetbozkan.quickfingers.data.db.preference.GameMode
import com.ahmetbozkan.quickfingers.data.db.preference.PreferencesManager
import com.ahmetbozkan.quickfingers.data.usecase.preference.GetPreferencesFlowUseCase
import com.ahmetbozkan.quickfingers.data.usecase.preference.UpdateGameModeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StartGameViewModel @Inject constructor(
    private val updateGameModeUseCase: UpdateGameModeUseCase,
    private val getPreferencesFlowUseCase: GetPreferencesFlowUseCase
) : ViewModel() {

    val preferencesFlow = getPreferencesFlowUseCase.invoke()

    fun onGameModeChanged(gameMode: GameMode) = viewModelScope.launch {
        updateGameModeUseCase.invoke(gameMode)
    }
}