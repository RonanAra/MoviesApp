package com.example.moviesapp.presentation.moviedetails.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.moviesapp.data.model.Movie
import com.example.moviesapp.data.repository.database.MovieDaoRepositoryImp
import com.example.moviesapp.domain.usecase.DetailsUseCase
import com.example.moviesapp.presentation.base.BaseViewModel
import kotlinx.coroutines.launch

class DetailsViewModel constructor(
    private val detailsDaoRepository: MovieDaoRepositoryImp,
    private val similarUseCase: DetailsUseCase
) : BaseViewModel() {

    val isMovieFavoriteData = MutableLiveData<Boolean>()

    private val _onSuccessSimilarMovies: MutableLiveData<List<Movie>> =
        MutableLiveData()
    val onSuccessSimilarMovies: LiveData<List<Movie>>
        get() = _onSuccessSimilarMovies


    fun getSimilar(movieId: Int) {
        viewModelScope.launch {
            callApi(
                suspend { similarUseCase.getSimilar(movieId) },
                onSuccess = {
                    val result = it as List<*>
                    _onSuccessSimilarMovies.postValue(
                        result.filterIsInstance<Movie>()
                    )
                }
            )
        }
    }


    fun insertMovieFavorite(movie: Movie) {
        viewModelScope.launch {
            detailsDaoRepository.insert(movie)
        }
    }

    fun deleteMovieFavorite(movie: Movie) {
        viewModelScope.launch {
            detailsDaoRepository.delete(movie)
        }
    }

    fun isMovieFavorite(movie: Movie) {
        viewModelScope.launch {
            isMovieFavoriteData.value = detailsDaoRepository.getAllMovieFavorites().contains(movie)
        }
    }
}