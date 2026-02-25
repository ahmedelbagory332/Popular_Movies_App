package com.example.data.di

import com.example.core.network.api.ApiServices
import com.example.data.repositoryImpl.MovieDetailsRepositoryImpl
import com.example.domain.repository.MovieDetailsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MovieDetailsRepositoryModule {

    @Provides
    @Singleton
    fun provideMovieRepository(api: ApiServices): MovieDetailsRepository =
        MovieDetailsRepositoryImpl(api)
}