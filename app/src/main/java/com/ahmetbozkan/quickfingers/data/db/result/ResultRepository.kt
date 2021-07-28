package com.ahmetbozkan.quickfingers.data.db.result

import com.ahmetbozkan.quickfingers.data.model.Result
import javax.inject.Inject

class ResultRepository @Inject constructor(
    private val resultDao: ResultDao
) {

    suspend fun insert(result: Result) {
        resultDao.insert(result)
    }

    suspend fun delete(result: Result) {
        resultDao.delete(result)
    }

}