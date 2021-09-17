package com.ahmetbozkan.quickfingers.util

import androidx.room.TypeConverter
import com.ahmetbozkan.quickfingers.data.model.preference.GameMode

class Converters {

    @TypeConverter
    fun fromGameMode(gameMode: GameMode): String = gameMode.name

    @TypeConverter
    fun toGameMode(gameMode: String): GameMode = enumValueOf(gameMode)
}