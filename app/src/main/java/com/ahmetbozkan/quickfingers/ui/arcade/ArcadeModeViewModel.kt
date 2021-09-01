package com.ahmetbozkan.quickfingers.ui.arcade

import androidx.lifecycle.*
import com.ahmetbozkan.quickfingers.data.db.preference.GameMode
import com.ahmetbozkan.quickfingers.data.model.Result
import com.ahmetbozkan.quickfingers.data.usecase.word.GetWordUseCase
import com.ahmetbozkan.quickfingers.util.Constants
import com.ahmetbozkan.quickfingers.util.SingleLiveEvent
import com.ahmetbozkan.quickfingers.util.extension.orZero
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArcadeModeViewModel @Inject constructor(
    private val getWordUseCase: GetWordUseCase,
    state: SavedStateHandle
) : ViewModel() {

    companion object {
        const val TIME_STATE = "time_State"
    }

    private var timerJob: Job? = null

    private val timePassed = MutableLiveData(Constants.INITIAL_VALUE_ZERO)

    private val _time = state.getLiveData(TIME_STATE, Constants.ARCADE_MODE_STARTING_TIME)
    val time: LiveData<Int> get() = _time

    private val _word = MutableLiveData<String>()
    val word: LiveData<String> get() = _word

    private val correct = MutableLiveData(Constants.INITIAL_VALUE_ZERO)
    private val wrong = MutableLiveData(Constants.INITIAL_VALUE_ZERO)

    private val _score = MutableLiveData(Constants.INITIAL_VALUE_ZERO)
    val score: LiveData<Int> get() = _score

    private var isStarted: Boolean = false

    private val _isFinished = SingleLiveEvent<Boolean>()
    val isFinished: LiveData<Boolean> get() = _isFinished

    init {
        getRandomWord()
    }

    private fun getRandomWord() = viewModelScope.launch {
        val word = getWordUseCase.invoke(Constants.LANGUAGE_TR)
        _word.postValue(word)
    }

    fun onEnterPressed(word: String) {
        if(!isStarted) {
            startTimer()
            isStarted = true
        }

        getRandomWord()
        evaluateWordInput(word)
    }

    private fun startTimer() {
        timerJob = viewModelScope.launch {
            while(_time.value!! > 0) {
                decreaseTimer()
                delay(Constants.COUNTDOWN_INTERVAL)
            }

            _isFinished.postValue(true)
        }
    }

    private fun evaluateWordInput(word: String) {
        if(word.trim() == _word.value) {
            correct.value = (correct.value!! + 1)
            _score.value = (_score.value!! + 2)
            incrementTimer()
        }
        else {
            wrong.value = (wrong.value!! + 1)
            _score.value = (_score.value!! - 1)
        }
    }

    fun onReplayClicked() {
        timerJob?.cancel()
        isStarted = false

        resetValues()
    }

    private fun resetValues() {
        _time.value = Constants.ARCADE_MODE_STARTING_TIME
        correct.value = Constants.INITIAL_VALUE_ZERO
        wrong.value = Constants.INITIAL_VALUE_ZERO
        _score.value = Constants.INITIAL_VALUE_ZERO

        getRandomWord()
    }

    private fun incrementTimer() {
        _time.value = (_time.value!! + 1)
    }

    private fun decreaseTimer() {
        _time.value = (_time.value!! - 1)
        timePassed.value = (timePassed.value!! + 1)
    }

    fun onGameFinished(): Result = calculateResult()


    private fun calculateResult(): Result {
        val total = wrong.value?.plus(correct.value!!)!!
        val accuracy: Double = String.format(
            "%.2f", (correct.value?.toDouble()!! / total) * 100
        ).toDouble()

        return Result(
            gameMode = GameMode.ARCADE,
            score = score.value.orZero(),
            correct = correct.value.orZero(),
            wrong = wrong.value.orZero(),
            accuracy = accuracy,
            timePassed = timePassed.value.orZero()
        )
    }

}