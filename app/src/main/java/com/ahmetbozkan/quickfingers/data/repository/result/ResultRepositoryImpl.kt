package com.ahmetbozkan.quickfingers.data.repository.result

import androidx.lifecycle.LiveData
import com.ahmetbozkan.quickfingers.data.db.preference.GameMode
import com.ahmetbozkan.quickfingers.data.db.result.ResultDao
import com.ahmetbozkan.quickfingers.data.model.Result
import javax.inject.Inject

class ResultRepositoryImpl @Inject constructor(
    private val resultDao: ResultDao
) : ResultRepository {

    override suspend fun insert(result: Result) {
        resultDao.insert(result)
    }

    override suspend fun delete(result: Result) {
        resultDao.delete(result)
    }

    override fun getResults(gameMode: GameMode): LiveData<List<Result>> =
        resultDao.getResults(gameMode)

}