package com.example.moviesapp.presentation.home

sealed interface HomeEvent {
    data object LoadMovies: HomeEvent
}