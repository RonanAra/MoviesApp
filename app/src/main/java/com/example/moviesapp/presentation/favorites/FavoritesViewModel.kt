package com.example.moviesapp.presentation.favorites

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesapp.data.model.Movie
import com.example.moviesapp.data.repository.database.MovieDaoRepositoryImp
import kotlinx.coroutines.launch

class FavoritesViewModel(
    private val movieDaoRepository: MovieDaoRepositoryImp
) : ViewModel() {

    private val listFavorites = MutableLiveData<ArrayList<Movie>>()

    val favoritesLivedata: LiveData<ArrayList<Movie>>
        get() = listFavorites

    init {
        getAllMovieFavorites()
    }

     fun getAllMovieFavorites() {
        viewModelScope.launch {
            listFavorites.value = movieDaoRepository.getAllMovieFavorites()
        }
    }

}