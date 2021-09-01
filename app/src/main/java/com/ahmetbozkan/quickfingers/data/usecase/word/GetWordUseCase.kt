package com.ahmetbozkan.quickfingers.data.usecase.word

import com.ahmetbozkan.quickfingers.data.repository.word.WordRepository
import javax.inject.Inject

class GetWordUseCase @Inject constructor(
    private val repository: WordRepository
) {

    suspend operator fun invoke(language: String): String {
        return repository.getRandomWord(language)
    }
}