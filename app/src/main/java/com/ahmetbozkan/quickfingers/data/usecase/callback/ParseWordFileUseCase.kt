package com.ahmetbozkan.quickfingers.data.usecase.callback

import com.ahmetbozkan.quickfingers.data.model.Word
import com.ahmetbozkan.quickfingers.data.repository.callback.WordDbCallbackRepository
import javax.inject.Inject

class ParseWordFileUseCase @Inject constructor(
    private val repository: WordDbCallbackRepository
) {

    val words = invoke()

    private fun invoke(): List<Word> = repository.parseWordFile()

}