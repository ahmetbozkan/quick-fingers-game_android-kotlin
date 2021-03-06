package com.ahmetbozkan.quickfingers.data.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ahmetbozkan.quickfingers.data.model.preference.GameMode
import kotlinx.android.parcel.Parcelize
import java.text.DateFormat

@Entity(tableName = "results")
@Parcelize
data class Result(
    @ColumnInfo(name = "game_mode") val gameMode: GameMode?,
    @ColumnInfo(name = "score") val score: Int?,
    @ColumnInfo(name = "correct") val correct: Int?,
    @ColumnInfo(name = "wrong") val wrong: Int?,
    @ColumnInfo(name = "accuracy") val accuracy: Double?,
    @ColumnInfo(name = "wpm") val wordsPerMinute: Int? = 0,
    @ColumnInfo(name = "time_passed") val timePassed: Int? = 60,
    @ColumnInfo(name = "date_saved") val dateSaved: Long = System.currentTimeMillis(),
    @PrimaryKey(autoGenerate = true) val id: Int = 0
) : Parcelable {
    val dateSavedFormatted: String
        get() = DateFormat.getDateTimeInstance().format(dateSaved)
}
