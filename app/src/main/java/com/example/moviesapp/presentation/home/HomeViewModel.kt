package com.example.moviesapp.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesapp.domain.repository.MoviesRepository
import com.example.moviesapp.utils.extensions.launchSuspendFunZip
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
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
        viewModelScope.launchSuspendFunZip(
            firstBlockToRun = { repository.getTopRated(1) },
            secondBlockToRun = { repository.getPopular(1) },
            thirdBlockToRun = { repository.getRecommend(1) },
            onSuccess = { movies ->
                _uiState.update {
                    HomeUiState.Movies(
                        popularMovies = movies.first,
                        recommendedMovies = movies.third,
                        topRatedMovies = movies.first
                    )
                }
            }
        )
    }
}