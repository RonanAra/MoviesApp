package com.example.moviesapp.domain.repository

import com.example.moviesapp.data.retrofit.MoviesService
import com.example.moviesapp.data.retrofit.MoviesService.tmdbApi
import com.example.moviesapp.presentation.base.BaseRepository
import com.example.moviesapp.utils.ResponseApi

class DetailsRepository: BaseRepository() {

    suspend fun getMovieById(id: Int): ResponseApi {
        return safeApiCall {
            MoviesService.tmdbApi.getMovieById(id)
        }
    }
}