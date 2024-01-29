package com.mmreda.mohamedredatask.presentation.di

import com.mmreda.mohamedredatask.utils.SharedPreferenceManger
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideSharedPreferenceManager(): SharedPreferenceManger {
        return SharedPreferenceManger()
    }
}