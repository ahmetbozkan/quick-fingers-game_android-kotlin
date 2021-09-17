package com.ahmetbozkan.quickfingers.ui.scoreboard.arcade

import androidx.lifecycle.ViewModel
import com.ahmetbozkan.quickfingers.data.model.preference.GameMode
import com.ahmetbozkan.quickfingers.data.usecase.result.GetResultsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ArcadeScoreboardViewModel @Inject constructor(
    getResultsUseCase: GetResultsUseCase
) : ViewModel() {

    val results = getResultsUseCase.invoke(GameMode.ARCADE)
}