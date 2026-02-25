package com.example.data.di

import com.example.core.network.api.ApiServices
import com.example.data.repositoryImpl.MovieRepositoryImpl
import com.example.domain.repository.MovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideMovieRepository(api: ApiServices): MovieRepository =
        MovieRepositoryImpl(api)
}