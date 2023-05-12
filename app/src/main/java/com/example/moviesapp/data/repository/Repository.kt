package com.example.moviesapp.data.repository.api

import com.example.moviesapp.data.service.MoviesService
import com.example.moviesapp.data.model.MovieResult
import com.example.moviesapp.data.model.MovieSimilarResults
import com.example.moviesapp.data.model.MovieTopRated
import com.example.moviesapp.data.model.RecommendResult

interface Repository {
    suspend fun getPopular(page: Int): MovieResult
    suspend fun getRecommend(page: Int): RecommendResult
    suspend fun getTopRated(page: Int): MovieTopRated
    suspend fun getSimilar(movieId: Int): MovieSimilarResults
    suspend fun search(titleMovie: String): MovieResult
}

class RepositoryImpl(
    private val service: MoviesService
) : Repository {

    override suspend fun getPopular(page: Int): MovieResult {
        return service.getPopularMovies(page)
    }

    override suspend fun getRecommend(page: Int): RecommendResult {
        return service.getRecommended(page)
    }

    override suspend fun getTopRated(page: Int): MovieTopRated {
        return service.getTopRated(page)
    }

    override suspend fun getSimilar(movieId: Int): MovieSimilarResults {
        return service.getSimilar(movieId)
    }

    override suspend fun search(titleMovie: String): MovieResult {
        return service.getSearchMovie(titleMovie)
    }
}