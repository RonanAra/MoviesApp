package com.example.moviesapp.domain.repository

import com.example.moviesapp.domain.entity.MoviesModel

interface MoviesRepository {
    suspend fun getPopular(page: Int): List<MoviesModel>
    suspend fun getRecommend(page: Int): List<MoviesModel>
    suspend fun getTopRated(page: Int): List<MoviesModel>
    suspend fun getSimilar(movieId: Int): List<MoviesModel>
    suspend fun search(titleMovie: String): List<MoviesModel>
}