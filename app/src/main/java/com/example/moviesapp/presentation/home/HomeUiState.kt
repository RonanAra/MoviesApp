package com.example.moviesapp.presentation.home

import androidx.paging.PagingData
import com.example.moviesapp.domain.entity.MoviesModel
import kotlinx.coroutines.flow.Flow

sealed interface HomeUiState {
    data class Loading(val loading: Boolean) : HomeUiState
    data class Movies(
        val popularMovies: Flow<PagingData<MoviesModel>>,
        val recommendedMovies: Flow<PagingData<MoviesModel>>,
        val topRatedMovies: Flow<PagingData<MoviesModel>>
    ) : HomeUiState
    data class Error(val message: String) : HomeUiState
}