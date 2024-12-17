package com.example.moviesapp.data.repository

import com.example.moviesapp.domain.models.MoviesModel
import com.example.moviesapp.data.datasource.MoviesDataSource

interface MoviesRepository {
    suspend fun getPopular(page: Int): List<MoviesModel>
    suspend fun getRecommend(page: Int): List<MoviesModel>
    suspend fun getTopRated(page: Int): List<MoviesModel>
    suspend fun getSimilar(movieId: Int): List<MoviesModel>
    suspend fun search(titleMovie: String): List<MoviesModel>
}

class MoviesRepositoryImpl(
    private val moviesDataSource: MoviesDataSource
) : MoviesRepository {
    override suspend fun getPopular(page: Int): List<MoviesModel> {
        return moviesDataSource.getPopular(page).results.map { it.toMoviesModel() }
    }

    override suspend fun getRecommend(page: Int): List<MoviesModel> {
        return moviesDataSource.getRecommend(page).results.map { it.toMoviesModel() }
    }

    override suspend fun getTopRated(page: Int): List<MoviesModel> {
        return moviesDataSource.getTopRated(page).results.map { it.toMoviesModel() }
    }

    override suspend fun getSimilar(movieId: Int): List<MoviesModel> {
        return moviesDataSource.getSimilar(movieId).results.map { it.toMoviesModel() }
    }

    override suspend fun search(titleMovie: String): List<MoviesModel> {
        return moviesDataSource.search(titleMovie).results.map { it.toMoviesModel() }
    }
}