package com.example.moviesapp.api

import com.example.moviesapp.base.BaseRepository
import com.example.moviesapp.utils.ResponseApi

class HomeRepository: BaseRepository() {


    suspend fun getPopular(): ResponseApi {
        return safeApiCall {
            MoviesService.tmdbApi.getPopularMovies()
        }
    }
}