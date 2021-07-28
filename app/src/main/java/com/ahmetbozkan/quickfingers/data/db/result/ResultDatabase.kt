package com.ahmetbozkan.quickfingers.data.db.result

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ahmetbozkan.quickfingers.data.model.Result
import com.ahmetbozkan.quickfingers.util.Converters

@Database(entities = [Result::class], version = 1)
@TypeConverters(Converters::class)
abstract class ResultDatabase : RoomDatabase() {

    abstract fun resultDao(): ResultDao

}