package com.ahmetbozkan.quickfingers

import androidx.lifecycle.ViewModel
import com.ahmetbozkan.quickfingers.data.usecase.callback.GetWordCountUseCase
import com.ahmetbozkan.quickfingers.data.usecase.preference.GetAppInfoFlowUseCase
import com.ahmetbozkan.quickfingers.data.usecase.preference.UpdateWordsDownloadedUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    getWordCountUseCase: GetWordCountUseCase,
    getAppInfoFlowUseCase: GetAppInfoFlowUseCase,
    private val updateWordsDownloadedUseCase: UpdateWordsDownloadedUseCase
) : ViewModel() {

    val wordCount = getWordCountUseCase.invoke()

    val appInfo = getAppInfoFlowUseCase.invoke()

    suspend fun updateWordsDownloaded(downloaded: Boolean) {
        updateWordsDownloadedUseCase.invoke(downloaded)
    }

}