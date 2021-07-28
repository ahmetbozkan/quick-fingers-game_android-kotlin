package com.ahmetbozkan.quickfingers.ui.mode.arcade

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahmetbozkan.quickfingers.data.GameMode
import com.ahmetbozkan.quickfingers.data.GameRepository
import com.ahmetbozkan.quickfingers.data.model.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject

@HiltViewModel
class ArcadeModeViewModel @Inject constructor(
    private val repository: GameRepository
) : ViewModel() {

    private var timerScope: CoroutineScope = CoroutineScope(viewModelScope.coroutineContext)

    private val _trWord = MutableLiveData<String>()
    val randomTrWord: LiveData<String> get() = _trWord

    private val correct = MutableLiveData(0)
    private val wrong = MutableLiveData(0)

    private val _score = MutableLiveData(0)
    val score: LiveData<Int> get() = _score

    private val timePassed = MutableLiveData(1)
    private val _time = MutableLiveData(10)
    val time: LiveData<Int> get() = _time

    var isStarted: Boolean = false

    init {
        getRandomWord()
    }

    fun getRandomWord() = viewModelScope.launch {
        _trWord.value = repository.getRandomWord("tr")
    }

    fun onEnterPressed(text: String) {
        if (text.trim() == randomTrWord.value) {
            correct.value = correct.value?.plus(1)
            _score.value = _score.value?.plus(2)
            _time.value = _time.value?.plus(1)
        } else {
            wrong.value = wrong.value?.plus(1)
            _score.value = _score.value?.minus(1)
        }
    }

    fun onGameStarted() {
        isStarted = true
        startTimer()
    }

    private fun startTimer() = timerScope.launch {
        while (time.value!! > 0) {
            if(isStarted) {
                _time.value = time.value?.minus(1)
                timePassed.value = timePassed.value?.plus(1)
                delay(1000)
            }
        }
        cancel()
    }

    fun onReplayClick() {
        isStarted = false
        timerScope.cancel()
        getRandomWord()

        correct.value = 0
        wrong.value = 0
        _score.value = 0
        timePassed.value = 1
        _time.value = 10
    }

    fun onGameFinish(): Result = calculateResult()

    private fun calculateResult(): Result {
        val total = wrong.value?.plus(correct.value!!)!!
        val accuracy: Double = String.format(
            "%.2f", (correct.value?.toDouble()!! / total) * 100
        ).toDouble()

        return Result(
            gameMode = GameMode.ARCADE,
            score = score.value,
            correct = correct.value,
            wrong = wrong.value,
            accuracy = accuracy,
            timePassed = timePassed.value
        )
    }

}