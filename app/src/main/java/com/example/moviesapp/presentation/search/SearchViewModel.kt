package com.example.moviesapp.presentation.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesapp.data.model.Movie
import com.example.moviesapp.data.repository.api.Repository
import kotlinx.coroutines.launch

class SearchViewModel(
    private val repository: Repository
) : ViewModel() {

    private val _onSuccessSearch: MutableLiveData<List<Movie>> = MutableLiveData()
    val onSuccessSearch: LiveData<List<Movie>>
        get() = _onSuccessSearch

    fun getSearch(titleMovie: String) {
        viewModelScope.launch {
           _onSuccessSearch.value = repository.search(titleMovie).results
        }
    }
}