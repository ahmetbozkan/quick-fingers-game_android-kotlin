package com.ahmetbozkan.quickfingers.data.usecase.result

import androidx.lifecycle.LiveData
import com.ahmetbozkan.quickfingers.data.db.preference.GameMode
import com.ahmetbozkan.quickfingers.data.model.Result
import com.ahmetbozkan.quickfingers.data.repository.result.ResultRepository
import javax.inject.Inject

class GetResultsUseCase @Inject constructor(
    private val repository: ResultRepository
) {

    operator fun invoke(gameMode: GameMode): LiveData<List<Result>> {
        return repository.getResults(gameMode)
    }

}