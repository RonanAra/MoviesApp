package com.example.moviesapp.data.repository.api

import com.example.moviesapp.data.retrofit.MoviesService
import com.example.moviesapp.presentation.base.BaseRepository
import com.example.moviesapp.utils.ResponseApi

class SearchApiRepository : BaseRepository() {

    suspend fun getSearch(titleMovie: String): ResponseApi {
        return safeApiCall {
            MoviesService.tmdbApi.getSearchMovie(titleMovie)
        }
    }
}