package com.ahmetbozkan.quickfingers.di

import android.app.Application
import androidx.room.Room
import com.ahmetbozkan.quickfingers.data.db.result.ResultDatabase
import com.ahmetbozkan.quickfingers.data.db.word.WordDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideWordDatabase(
        application: Application,
        callback: WordDatabase.Callback
    ) = Room.databaseBuilder(application, WordDatabase::class.java, "word_database")
        .fallbackToDestructiveMigration()
        .addCallback(callback)
        .build()

    @Provides
    fun provideWordDao(database: WordDatabase) = database.wordDao()

    @ApplicationScope
    @Provides
    @Singleton
    fun provideApplicationScope() = CoroutineScope(SupervisorJob())

    @Provides
    @Singleton
    fun provideResultDatabase(
        application: Application
    ) = Room.databaseBuilder(application, ResultDatabase::class.java, "result_database")
        .fallbackToDestructiveMigration()
        .build()

    @Provides
    fun provideResultDao(database: ResultDatabase) = database.resultDao()

}

@Retention(AnnotationRetention.RUNTIME)
@Qualifier
annotation class ApplicationScope
