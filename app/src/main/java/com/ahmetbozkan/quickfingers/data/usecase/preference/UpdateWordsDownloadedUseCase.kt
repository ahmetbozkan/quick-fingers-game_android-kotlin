package com.ahmetbozkan.quickfingers.data.usecase.preference

import com.ahmetbozkan.quickfingers.data.repository.preference.PreferencesManagerRepository
import javax.inject.Inject

class UpdateWordsDownloadedUseCase @Inject constructor(
    private val repository: PreferencesManagerRepository
) {

    suspend operator fun invoke(downloaded: Boolean) =
        repository.updateWordsDownloaded(downloaded)
}