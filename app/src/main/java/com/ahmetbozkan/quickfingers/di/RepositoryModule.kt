package com.ahmetbozkan.quickfingers.di

import com.ahmetbozkan.quickfingers.data.repository.preference.PreferencesManagerRepository
import com.ahmetbozkan.quickfingers.data.repository.preference.PreferencesManagerRepositoryImpl
import com.ahmetbozkan.quickfingers.data.repository.result.ResultRepository
import com.ahmetbozkan.quickfingers.data.repository.result.ResultRepositoryImpl
import com.ahmetbozkan.quickfingers.data.repository.word.WordRepository
import com.ahmetbozkan.quickfingers.data.repository.word.WordRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @Binds
    @ViewModelScoped
    abstract fun bindPreferencesManagerRepository(
        preferencesManagerRepository: PreferencesManagerRepositoryImpl
    ): PreferencesManagerRepository

    @Binds
    @ViewModelScoped
    abstract fun bindResultRepository(resultRepository: ResultRepositoryImpl): ResultRepository

    @Binds
    @ViewModelScoped
    abstract fun bindWordRepository(wordRepository: WordRepositoryImpl): WordRepository

}