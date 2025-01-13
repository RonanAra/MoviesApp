package com.example.moviesapp.domain.repository

import androidx.paging.PagingData
import com.example.moviesapp.domain.entity.MoviesModel
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {
    suspend fun getPopular(): Flow<PagingData<MoviesModel>>
    suspend fun getRecommend(): Flow<PagingData<MoviesModel>>
    suspend fun getTopRated(): Flow<PagingData<MoviesModel>>
    suspend fun getSimilar(movieId: Int): List<MoviesModel>
    suspend fun search(titleMovie: String): List<MoviesModel>
}