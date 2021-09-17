package com.ahmetbozkan.quickfingers.data.db.result

import androidx.lifecycle.LiveData
import androidx.room.*
import com.ahmetbozkan.quickfingers.data.model.Result
import com.ahmetbozkan.quickfingers.data.model.preference.GameMode

@Dao
interface ResultDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(result: Result)

    @Delete
    suspend fun delete(result: Result)

    @Query("SELECT * FROM results WHERE game_mode = :gameMode")
    fun getResults(gameMode: GameMode): LiveData<List<Result>>
}