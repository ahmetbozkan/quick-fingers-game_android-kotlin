package com.ahmetbozkan.quickfingers

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahmetbozkan.quickfingers.data.db.word.WordDao
import com.ahmetbozkan.quickfingers.data.model.Word
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BaseViewModel @Inject constructor(
    private val wordDao: WordDao
) : ViewModel() {

    fun invokeDatabaseCallback() = viewModelScope.launch {
        wordDao.insert(
            Word("deneme", "tr")
        )
    }

}