package com.example.moviesapp.features.repository

import com.example.moviesapp.api.MoviesService
import com.example.moviesapp.base.BaseRepository
import com.example.moviesapp.utils.ResponseApi

class HomeRepository: BaseRepository() {


    suspend fun getPopular(): ResponseApi {
        return safeApiCall {
            MoviesService.tmdbApi.getPopularMovies()
        }
    }
}