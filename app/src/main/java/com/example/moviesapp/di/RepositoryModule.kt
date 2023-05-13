package com.example.moviesapp.di

import com.example.moviesapp.data.remote.datasource.MoviesDataSource
import com.example.moviesapp.data.remote.datasource.MoviesDataSourceImpl
import com.example.moviesapp.data.remote.repository.MoviesRepository
import com.example.moviesapp.data.remote.repository.MoviesRepositoryImpl
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