package com.ahmetbozkan.quickfingers.util.extension

import android.content.Context
import java.io.IOException

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