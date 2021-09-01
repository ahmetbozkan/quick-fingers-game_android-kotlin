package com.ahmetbozkan.quickfingers.data.db.word

import android.content.Context
import com.ahmetbozkan.quickfingers.data.model.Word
import com.ahmetbozkan.quickfingers.util.extension.getJsonDataFromFile
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class DatabaseCallbackRepository @Inject constructor(
    @ApplicationContext private val context: Context
) {

    fun words(): List<Word> = parseWordFile()

    private fun parseWordFile(): List<Word> {
        val wordData = getJsonDataFromFile(context, "words.json")

        val gson = Gson()
        val wordListType = object : TypeToken<List<Word>>() {}.type

        return gson.fromJson(wordData, wordListType)
    }

}