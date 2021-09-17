package com.ahmetbozkan.quickfingers.util.extension

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import java.io.IOException

private const val TAG = "DataStoreExtensions"

val Context.gameModeDataStore: DataStore<Preferences> by preferencesDataStore(name = "game_modes")

val Context.appInfoDataStore: DataStore<Preferences> by preferencesDataStore(name = "application_info")

fun DataStore<Preferences>.handleException(): Flow<Preferences> =
    this.data.catch { exception ->
        if (exception is IOException) {
            Log.e(TAG, "Error reading preferences.", exception)
            emit(emptyPreferences())
        } else {
            throw exception
        }
    }


///**
// * maps the given DataStore
// * @param key is the Key that used to store value with
// * @param defaultValue is the value that will be returned if return value is null
// * @return flow of stored object
// */
//fun <T> DataStore<Preferences>.mapDataStore(key: Preferences.Key<T>, defaultValue: T): Flow<T> {
//    return this.data.catch { exception ->
//        if (exception is IOException)
//            emit(emptyPreferences())
//        else
//            throw  exception
//    }.map { preferences ->
//        val preference = preferences[key] ?: defaultValue
//
//        preference
//    }
//}