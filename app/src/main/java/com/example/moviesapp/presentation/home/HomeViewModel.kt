package com.example.moviesapp.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.moviesapp.domain.repository.MoviesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: MoviesRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<HomeUiState>(HomeUiState.Loading(false))
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    fun onEvent(event: HomeEvent) {
        when (event) {
            HomeEvent.LoadMovies -> loadMovies()
        }
    }

    private fun loadMovies() {
        viewModelScope.launch {
            val popularMoviesFlow = repository.getPopular().cachedIn(viewModelScope)
            val topRatedMoviesFlow = repository.getTopRated().cachedIn(viewModelScope)
            val recommendedMoviesFlow = repository.getRecommend().cachedIn(viewModelScope)

            _uiState.update {
                HomeUiState.Movies(
                    popularMovies = popularMoviesFlow,
                    recommendedMovies = recommendedMoviesFlow,
                    topRatedMovies = topRatedMoviesFlow
                )
            }
        }
    }
}