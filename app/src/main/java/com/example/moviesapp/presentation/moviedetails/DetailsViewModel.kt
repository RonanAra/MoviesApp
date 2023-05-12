package com.example.moviesapp.presentation.moviedetails

import androidx.lifecycle.ViewModel
import com.example.moviesapp.data.repository.database.MovieDaoRepositoryImp

class DetailsViewModel constructor(
    private val detailsDaoRepository: MovieDaoRepositoryImp,
) : ViewModel() {

}