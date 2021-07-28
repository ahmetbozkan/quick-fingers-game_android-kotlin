package com.ahmetbozkan.quickfingers.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "words")
data class Word(
    val word: String,
    val language: String,
    @PrimaryKey(autoGenerate = true) val id: Int = 0
)
