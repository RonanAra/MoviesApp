package com.example.moviesapp.presentation.moviedetails.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.moviesapp.data.model.Movie
import com.example.moviesapp.domain.usecase.DetailsUseCase
import com.example.moviesapp.presentation.base.BaseViewModel
import kotlinx.coroutines.launch

class DetailsViewModel constructor(
    private val detailsUseCase: DetailsUseCase
) : BaseViewModel() {

    private val _onSuccessMovieById: MutableLiveData<Movie> = MutableLiveData()
    val onSuccessMovieById: LiveData<Movie>
        get() = _onSuccessMovieById


    fun getMovieById(movieId: Int) {
        viewModelScope.launch {
            callApi(
                suspend { detailsUseCase.getMovieById(movieId) },
                onSuccess = {
                    _onSuccessMovieById.postValue(it as? Movie)
                }
            )
        }
    }
}