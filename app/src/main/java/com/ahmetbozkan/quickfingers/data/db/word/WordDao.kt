package com.ahmetbozkan.quickfingers.data.db.word

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ahmetbozkan.quickfingers.data.model.Word

@Dao
interface WordDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg word: Word)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(word: Word)

    @Query("SELECT word FROM words WHERE language = :language ORDER BY RANDOM() LIMIT 1")
    suspend fun getRandomWord(
        language: String
    ): String
}