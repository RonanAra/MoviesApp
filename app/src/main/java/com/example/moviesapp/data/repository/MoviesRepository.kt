package com.example.moviesapp.data.repository

import com.example.moviesapp.domain.entity.MoviesModel
import com.example.moviesapp.data.datasource.MoviesDataSource
import com.example.moviesapp.data.mapper.mapperToMovieModel
import com.example.moviesapp.domain.repository.MoviesRepository
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor(
    private val moviesDataSource: MoviesDataSource
) : MoviesRepository {
    override suspend fun getPopular(page: Int): List<MoviesModel> {
        return moviesDataSource.getPopular(page).results.mapperToMovieModel()
    }

    override suspend fun getRecommend(page: Int): List<MoviesModel> {
        return moviesDataSource.getRecommend(page).results.mapperToMovieModel()
    }

    override suspend fun getTopRated(page: Int): List<MoviesModel> {
        return moviesDataSource.getTopRated(page).results.mapperToMovieModel()
    }

    override suspend fun getSimilar(movieId: Int): List<MoviesModel> {
        return moviesDataSource.getSimilar(movieId).results.mapperToMovieModel()
    }

    override suspend fun search(titleMovie: String): List<MoviesModel> {
        return moviesDataSource.search(titleMovie).results.mapperToMovieModel()
    }
}