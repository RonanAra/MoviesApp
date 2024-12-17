package com.example.moviesapp.data.datasource

import com.example.moviesapp.data.models.MoviesResponse
import com.example.moviesapp.data.service.MoviesService

interface MoviesDataSource {
    suspend fun getPopular(page: Int): MoviesResponse
    suspend fun getRecommend(page: Int): MoviesResponse
    suspend fun getTopRated(page: Int): MoviesResponse
    suspend fun getSimilar(movieId: Int): MoviesResponse
    suspend fun search(titleMovie: String): MoviesResponse
}

class MoviesDataSourceImpl(
    private val service: MoviesService
) : MoviesDataSource {

    override suspend fun getPopular(page: Int): MoviesResponse {
        return service.getPopularMovies(page)
    }

    override suspend fun getRecommend(page: Int): MoviesResponse {
        return service.getRecommendedMovies(page)
    }

    override suspend fun getTopRated(page: Int): MoviesResponse {
        return service.getTopRatedMovies(page)
    }

    override suspend fun getSimilar(movieId: Int): MoviesResponse {
        return service.getSimilarMovies(movieId)
    }

    override suspend fun search(titleMovie: String): MoviesResponse {
        return service.getSearchMovies(titleMovie)
    }
}