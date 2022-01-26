package com.example.moviesapp.data.repository.api

import com.example.moviesapp.presentation.base.BaseRepository
import com.example.moviesapp.data.retrofit.MoviesService
import com.example.moviesapp.utils.ResponseApi

class HomeRepository: BaseRepository() {

    suspend fun getPopular(page: Int): ResponseApi {
        return safeApiCall {
            MoviesService.tmdbApi.getPopularMovies(page)
        }
    }
}

