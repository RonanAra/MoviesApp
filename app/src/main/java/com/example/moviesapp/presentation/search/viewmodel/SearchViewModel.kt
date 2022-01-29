package com.example.moviesapp.presentation.search.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.moviesapp.data.model.Movie
import com.example.moviesapp.data.model.MovieResult
import com.example.moviesapp.domain.usecase.SearchUseCase
import com.example.moviesapp.presentation.base.BaseViewModel
import kotlinx.coroutines.launch

class SearchViewModel(
    private val searchUseCase: SearchUseCase
): BaseViewModel() {

    private val _onSuccessSearch: MutableLiveData<List<Movie>> =
        MutableLiveData()
    val onSuccessSearch: LiveData<List<Movie>>
        get() = _onSuccessSearch


    fun getSearch(titleMovie: String) {
        viewModelScope.launch {
            callApi(
                suspend { searchUseCase.getSearch(titleMovie) },
                onSuccess = {
                    val result = it as List<*>
                    _onSuccessSearch.postValue(
                        result.filterIsInstance<Movie>()
                    )
                }
            )
        }
    }

}