package com.example.moviesapp.presentation.home

import com.example.moviesapp.domain.entity.MoviesModel

sealed interface HomeUiState {
    data class Loading(val loading: Boolean) : HomeUiState
    data class Movies(
        val popularMovies: List<MoviesModel>,
        val recommendedMovies: List<MoviesModel>,
        val topRatedMovies: List<MoviesModel>
    ) : HomeUiState
    data class Error(val message: String) : HomeUiState
}