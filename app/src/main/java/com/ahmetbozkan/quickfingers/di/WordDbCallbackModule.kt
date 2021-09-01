package com.ahmetbozkan.quickfingers.di

import com.ahmetbozkan.quickfingers.data.repository.callback.WordDbCallbackRepository
import com.ahmetbozkan.quickfingers.data.repository.callback.WordDbCallbackRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class WordDbCallbackModule {

    @Binds
    @Singleton
    abstract fun bindWordDbCallbackRepository(repository: WordDbCallbackRepositoryImpl): WordDbCallbackRepository
}