package com.example.moviesapp.presentation.bookmarks.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesapp.data.model.Movie
import com.example.moviesapp.data.repository.database.MovieDaoRepositoryImp
import kotlinx.coroutines.launch

class BookViewModel(
    private val movieDaoRepository: MovieDaoRepositoryImp
) : ViewModel() {

    val listFavorites = MutableLiveData<ArrayList<Movie>?>()

    init {
        getAllMovieFavorites()
    }


    private fun getAllMovieFavorites() {
        viewModelScope.launch {
            listFavorites.value = movieDaoRepository.getAllMovieFavorites()
        }
    }

}