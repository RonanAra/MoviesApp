package com.example.moviesapp.data.repository.api

import com.example.moviesapp.presentation.base.BaseRepository
import com.example.moviesapp.data.retrofit.MoviesService
import com.example.moviesapp.utils.ResponseApi

class ApiRepository : BaseRepository() {

    suspend fun getPopular(page: Int): ResponseApi {
        return safeApiCall {
            MoviesService.tmdbApi.getPopularMovies(page)
        }
    }

    suspend fun getRecommend(page: Int): ResponseApi {
        return safeApiCall {
            MoviesService.tmdbApi.listRecommended(page)
        }
    }

    suspend fun getTopRated(page: Int): ResponseApi {
        return safeApiCall {
            MoviesService.tmdbApi.listTopRated(page)
        }
    }

}

