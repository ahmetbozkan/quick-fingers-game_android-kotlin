package com.ahmetbozkan.quickfingers.util

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import java.io.IOException

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "game_modes")

fun getJsonDataFromFile(context: Context, fileName: String): String? {
    val value: String

    try {
        value = context.assets.open(fileName).bufferedReader().use {
            it.readText()
        }
    } catch (ioException: IOException) {
        ioException.printStackTrace()
        return null
    }

    return value
}