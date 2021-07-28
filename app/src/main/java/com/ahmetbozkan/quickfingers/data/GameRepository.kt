package com.ahmetbozkan.quickfingers.data

import com.ahmetbozkan.quickfingers.data.db.word.WordDao
import javax.inject.Inject

class GameRepository @Inject constructor(
    private val wordDao: WordDao
) {

    suspend fun getRandomWord(
        language: String
    ): String = wordDao.getRandomWord(language)

}