package com.ahmetbozkan.quickfingers.data.repository.word

import androidx.lifecycle.LiveData

interface WordRepository {

    /**
     * get a random word from the room database
     * @param language of the word (en, tr)
     * @return word as String
     */
    suspend fun getRandomWord(language: String): String

    /**
     * get word count to invoke callback and determine if words inserted to db
     * @return count of the words as Integer type wrapped into LiveData
     */
    fun getWordCount(): LiveData<Int>
}