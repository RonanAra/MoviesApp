package com.example.moviesapp.di

import com.example.moviesapp.data.datasource.MoviesDataSource
import com.example.moviesapp.data.datasource.MoviesDataSourceImpl
import com.example.moviesapp.data.repository.MoviesRepositoryImpl
import com.example.moviesapp.domain.repository.MoviesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun bindMoviesRepository(repository: MoviesRepositoryImpl): MoviesRepository

    @Binds
    fun bindMoviesDataSource(dataSource: MoviesDataSourceImpl): MoviesDataSource
}