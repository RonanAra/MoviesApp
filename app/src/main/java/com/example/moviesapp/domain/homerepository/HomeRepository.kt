package com.example.moviesapp.domain.homerepository

import com.example.moviesapp.presentation.base.BaseRepository
import com.example.moviesapp.data.retrofit.MoviesService
import com.example.moviesapp.utils.ResponseApi

class HomeRepository: BaseRepository() {
    suspend fun getPopular(): ResponseApi {
        return safeApiCall {
            MoviesService.tmdbApi.getPopularMovies()
        }
    }
}