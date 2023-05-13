package com.example.moviesapp.data.remote.datasource

import com.example.moviesapp.data.remote.service.MoviesService
import com.example.moviesapp.data.remote.models.ListMoviesResponse

interface MoviesDataSource {
    suspend fun getPopular(page: Int): ListMoviesResponse
    suspend fun getRecommend(page: Int): ListMoviesResponse
    suspend fun getTopRated(page: Int): ListMoviesResponse
    suspend fun getSimilar(movieId: Int): ListMoviesResponse
    suspend fun search(titleMovie: String): ListMoviesResponse
}

class MoviesDataSourceImpl(
    private val service: MoviesService
) : MoviesDataSource {

    override suspend fun getPopular(page: Int): ListMoviesResponse {
        return service.getPopularMovies(page)
    }

    override suspend fun getRecommend(page: Int): ListMoviesResponse {
        return service.getRecommended(page)
    }

    override suspend fun getTopRated(page: Int): ListMoviesResponse {
        return service.getTopRated(page)
    }

    override suspend fun getSimilar(movieId: Int): ListMoviesResponse {
        return service.getSimilar(movieId)
    }

    override suspend fun search(titleMovie: String): ListMoviesResponse {
        return service.getSearchMovie(titleMovie)
    }
}