package com.example.moviesapp.domain.usecase

import com.example.moviesapp.extensions.getFullImageUrl
import com.example.moviesapp.data.repository.api.HomeRepository
import com.example.moviesapp.data.model.Popular
import com.example.moviesapp.data.model.Result

class HomeUseCase
    constructor(
    private val repository : HomeRepository
        ){


    fun setupMoviesList(list: Popular?): List<Result> {
        val movies = list?.results
        movies?.forEach { movie ->
            movie.poster_path = movie.poster_path?.getFullImageUrl()
            movie.backdrop_path = movie.backdrop_path?.getFullImageUrl()

        }
        return movies ?: listOf()
    }

}


