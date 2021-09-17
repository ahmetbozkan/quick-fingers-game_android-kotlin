package com.ahmetbozkan.quickfingers.ui.classic

import androidx.lifecycle.*
import com.ahmetbozkan.quickfingers.data.model.Result
import com.ahmetbozkan.quickfingers.data.model.preference.GameMode
import com.ahmetbozkan.quickfingers.data.usecase.word.GetWordUseCase
import com.ahmetbozkan.quickfingers.util.Constants
import com.ahmetbozkan.quickfingers.util.extension.formatEndingDecimals
import com.ahmetbozkan.quickfingers.util.extension.orZero
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ClassicModeViewModel @Inject constructor(
    private val getWordUseCase: GetWordUseCase,
    state: SavedStateHandle
) : ViewModel() {

    companion object {
        const val TIME_STATE = "time_state"
    }

    private val _time = state.getLiveData(TIME_STATE, Constants.CLASSIC_MODE_STARTING_TIME)
    val time: LiveData<Long> get() = _time

    private val _word = MutableLiveData<String>()
    val word: LiveData<String> get() = _word

    private val correct = MutableLiveData(Constants.INITIAL_VALUE_ZERO)
    private val wrong = MutableLiveData(Constants.INITIAL_VALUE_ZERO)

    private val _score = MutableLiveData(Constants.INITIAL_VALUE_ZERO)
    val score: LiveData<Int> get() = _score

    private val _startTimer = MutableLiveData(false)
    val startTimer: LiveData<Boolean> get() = _startTimer

    private var isStarted: Boolean = false

    init {
        getRandomWord()
    }

    private fun getRandomWord() = viewModelScope.launch {
        val word = getWordUseCase.invoke(Constants.LANGUAGE_TR)
        _word.postValue(word)
    }

    fun onEnterPressed(word: String) {
        getRandomWord()
        evaluateWordInput(word)

        if (!isStarted) {
            _startTimer.postValue(true)
            isStarted = true
        }
    }

    private fun evaluateWordInput(word: String) {
        if (word.trim() == _word.value) {
            correct.postValue(correct.value!! + 1)
            _score.postValue(_score.value!! + 2)
        } else {
            wrong.postValue(wrong.value!! + 1)
            _score.postValue(_score.value!! - 1)
        }
    }

    fun updateTimer(millisUntilFinished: Long) {
        _time.postValue(millisUntilFinished)
    }

    fun onReplayClicked() {
        if (isStarted) {
            _startTimer.postValue(false)
            isStarted = false
        }

        resetValues()
    }

    private fun resetValues() {
        _time.postValue(Constants.CLASSIC_MODE_STARTING_TIME)
        correct.postValue(Constants.INITIAL_VALUE_ZERO)
        wrong.postValue(Constants.INITIAL_VALUE_ZERO)
        _score.postValue(Constants.INITIAL_VALUE_ZERO)

        getRandomWord()
    }

    fun onGameFinish() = calculateResult()

    private fun calculateResult(): Result {
        val total = wrong.value?.plus(correct.value!!)!!

        val accuracy: Double = (correct.value?.toDouble()!! / total) * 100
        val accuracyFormatted = accuracy.formatEndingDecimals()

        val wpm = correct.value!! - wrong.value!!

        return Result(
            gameMode = GameMode.CLASSIC,
            score = score.value.orZero(),
            correct = correct.value.orZero(),
            wrong = wrong.value.orZero(),
            accuracy = accuracyFormatted,
            wordsPerMinute = if (wpm >= 0) wpm else 0
        )
    }

}