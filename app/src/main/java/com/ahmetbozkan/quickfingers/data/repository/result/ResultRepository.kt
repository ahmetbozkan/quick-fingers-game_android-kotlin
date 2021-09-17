package com.ahmetbozkan.quickfingers.data.repository.result

import androidx.lifecycle.LiveData
import com.ahmetbozkan.quickfingers.data.model.Result
import com.ahmetbozkan.quickfingers.data.model.preference.GameMode

interface ResultRepository {

    /**
     * insert result to the room db
     * @param result object which is calculated in end of the game
     */
    suspend fun insert(result: Result)

    /**
     * delete result from the room db
     * @param result model to be deleted
     */
    suspend fun delete(result: Result)

    /**
     * get results from the room db
     * @param gameMode to get specified results
     * @return list of result as LiveData
     */
    fun getResults(gameMode: GameMode): LiveData<List<Result>>

}