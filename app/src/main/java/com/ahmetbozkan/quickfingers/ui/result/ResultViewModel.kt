package com.ahmetbozkan.quickfingers.ui.result

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahmetbozkan.quickfingers.data.model.Result
import com.ahmetbozkan.quickfingers.data.usecase.result.InsertResultUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ResultViewModel @Inject constructor(
    private val insertResultUseCase: InsertResultUseCase
) : ViewModel() {

    private var saved: Boolean = false

    fun onSaveClick(result: Result) = viewModelScope.launch {
        if (!saved) {
            insertResultUseCase.invoke(result)
            saved = true
        }

    }

}