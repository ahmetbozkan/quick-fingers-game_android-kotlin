package com.ahmetbozkan.quickfingers.ui.result

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahmetbozkan.quickfingers.data.db.result.ResultRepository
import com.ahmetbozkan.quickfingers.data.model.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ResultViewModel @Inject constructor(
    private val repository: ResultRepository
): ViewModel() {

    var saved: Boolean = false

    fun onSaveClick(result: Result) = viewModelScope.launch {
        repository.insert(result)
    }

}