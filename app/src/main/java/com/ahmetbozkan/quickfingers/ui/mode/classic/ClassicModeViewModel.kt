package com.ahmetbozkan.quickfingers.ui.mode.classic

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahmetbozkan.quickfingers.data.GameMode
import com.ahmetbozkan.quickfingers.data.GameRepository
import com.ahmetbozkan.quickfingers.data.model.Result
import com.ahmetbozkan.quickfingers.util.extension.orZero
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ClassicModeViewModel @Inject constructor(
    private val repository: GameRepository
) : ViewModel() {

    private val _trWord = MutableLiveData<String>()
    val randomTrWord: LiveData<String> get() = _trWord

    private val correct = MutableLiveData(0)
    private val wrong = MutableLiveData(0)

    private val _score = MutableLiveData(0)
    val score: LiveData<Int> get() = _score

    private val _time = MutableLiveData<Long>(10000)
    val time: LiveData<Long> get() = _time

    var isStarted: Boolean = false

    init {
        getRandomWord()
    }

    fun getRandomWord() = viewModelScope.launch {
        _trWord.value = repository.getRandomWord("tr")
    }

    fun updateTimer(millisUntilFinished: Long) {
        _time.value = millisUntilFinished
    }

    fun onEnterPressed(text: String) {
        if (text.trim() == randomTrWord.value) {
            correct.value = correct.value?.plus(1)
            _score.value = _score.value?.plus(2)
        } else {
            wrong.value = wrong.value?.plus(1)
            _score.value = _score.value?.minus(1)
        }
    }

    fun onReplayClicked() {
        isStarted = false
        getRandomWord()

        correct.value = 0
        wrong.value = 0
        _score.value = 0
        _time.value = 10000
    }

    fun onGameFinish() = calculateResult()

    private fun calculateResult(): Result {
        val total = wrong.value?.plus(correct.value!!)!!
        val accuracy: Double = String.format(
            "%.2f", (correct.value?.toDouble()!! / total) * 100
        ).toDouble()

        val wpm = correct.value!! - wrong.value!!

        return Result(
            gameMode = GameMode.CLASSIC,
            score = score.value.orZero(),
            correct = correct.value.orZero(),
            wrong = wrong.value.orZero(),
            accuracy = accuracy,
            wordsPerMinute = if (wpm >= 0) wpm else 0
        )
    }

}