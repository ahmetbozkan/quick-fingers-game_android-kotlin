package com.ahmetbozkan.quickfingers.data.usecase.result

import com.ahmetbozkan.quickfingers.data.model.Result
import com.ahmetbozkan.quickfingers.data.repository.result.ResultRepository
import javax.inject.Inject

class DeleteResultUseCase @Inject constructor(
    private val repository: ResultRepository
) {

    suspend operator fun invoke(result: Result) {
        repository.delete(result)
    }
}