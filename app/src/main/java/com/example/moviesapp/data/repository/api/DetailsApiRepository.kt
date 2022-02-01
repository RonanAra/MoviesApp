package com.example.moviesapp.data.repository.api

import com.example.moviesapp.data.retrofit.MoviesService
import com.example.moviesapp.presentation.base.BaseRepository
import com.example.moviesapp.utils.ResponseApi

class DetailsApiRepository: BaseRepository() {

    suspend fun getMovieById(id: Int): ResponseApi {
        return safeApiCall {
            MoviesService.tmdbApi.getMovieById(id)
        }
    }
}