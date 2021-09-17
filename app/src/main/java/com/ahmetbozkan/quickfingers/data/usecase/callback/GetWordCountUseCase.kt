package com.ahmetbozkan.quickfingers.data.usecase.callback

import androidx.lifecycle.LiveData
import com.ahmetbozkan.quickfingers.data.repository.word.WordRepository
import javax.inject.Inject

class GetWordCountUseCase @Inject constructor(
    private val repository: WordRepository
) {

    operator fun invoke(): LiveData<Int> = repository.getWordCount()

}