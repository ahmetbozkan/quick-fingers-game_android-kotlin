package com.ahmetbozkan.quickfingers.data.repository.word

interface WordRepository {

    /**
     * get a random word from the room database
     * @param language of the word (en, tr)
     * @return word as String
     */
    suspend fun getRandomWord(language: String): String
}