package com.mmreda.mohamedredatask.presentation.di

import com.mmreda.mohamedredatask.data.remote.ApiService
import com.mmreda.mohamedredatask.data.repo.home.HomeRepoImpl
import com.mmreda.mohamedredatask.domain.repo.home.HomeRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepoModule {

    @Provides
    fun provideHomeRepo(apiService: ApiService): HomeRepo {
        return HomeRepoImpl(apiService)
    }
}