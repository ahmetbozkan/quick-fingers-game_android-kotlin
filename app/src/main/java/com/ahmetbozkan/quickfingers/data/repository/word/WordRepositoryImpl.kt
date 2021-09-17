package com.ahmetbozkan.quickfingers.data.repository.word

import androidx.lifecycle.LiveData
import com.ahmetbozkan.quickfingers.data.db.word.WordDao
import javax.inject.Inject

class WordRepositoryImpl @Inject constructor(
    private val wordDao: WordDao
) : WordRepository {

    override suspend fun getRandomWord(language: String): String =
        wordDao.getRandomWord(language)

    override fun getWordCount(): LiveData<Int> = wordDao.getWordCount()

}