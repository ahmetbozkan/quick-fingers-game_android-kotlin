package com.ahmetbozkan.quickfingers.ui.toplevel.scoreboard.arcade

import androidx.lifecycle.ViewModel
import com.ahmetbozkan.quickfingers.data.GameMode
import com.ahmetbozkan.quickfingers.data.db.result.ResultDao
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ArcadeScoreboardViewModel @Inject constructor(
    private val resultDao: ResultDao
) : ViewModel() {

    val results = resultDao.getResults(GameMode.ARCADE)
}