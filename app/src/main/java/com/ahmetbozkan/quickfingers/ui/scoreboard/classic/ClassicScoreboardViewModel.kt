package com.ahmetbozkan.quickfingers.ui.scoreboard.classic

import androidx.lifecycle.ViewModel
import com.ahmetbozkan.quickfingers.data.db.preference.GameMode
import com.ahmetbozkan.quickfingers.data.db.result.ResultDao
import com.ahmetbozkan.quickfingers.data.usecase.result.GetResultsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ClassicScoreboardViewModel @Inject constructor(
    private val getResultsUseCase: GetResultsUseCase
) : ViewModel() {

    val results = getResultsUseCase.invoke(GameMode.CLASSIC)
}