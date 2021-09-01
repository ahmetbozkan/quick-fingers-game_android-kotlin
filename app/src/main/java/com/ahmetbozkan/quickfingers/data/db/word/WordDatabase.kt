package com.ahmetbozkan.quickfingers.data.db.word

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.ahmetbozkan.quickfingers.data.model.Word
import com.ahmetbozkan.quickfingers.data.usecase.callback.ParseWordFileUseCase
import com.ahmetbozkan.quickfingers.di.ApplicationScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Provider

@Database(entities = [Word::class], version = 1)
abstract class WordDatabase : RoomDatabase() {

    abstract fun wordDao(): WordDao

    class Callback @Inject constructor(
        private val database: Provider<WordDatabase>,
        @ApplicationScope private val applicationScope: CoroutineScope,
        private val useCase: ParseWordFileUseCase
    ) : RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)

            val dao = database.get().wordDao()
            val words: List<Word> = useCase.words

            applicationScope.launch {
                dao.insert(*words.toTypedArray())
            }
        }
    }

}