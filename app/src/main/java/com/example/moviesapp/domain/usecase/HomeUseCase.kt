package com.example.moviesapp.domain.usecase

import com.example.moviesapp.extensions.getFullImageUrl
import com.example.moviesapp.data.repository.api.HomeApiRepository
import com.example.moviesapp.data.model.MovieResult
import com.example.moviesapp.data.model.Movie

class HomeUseCase(
    private val repository: HomeApiRepository
) {


    fun setupMoviesList(list: MovieResult?): List<Movie> {
        val movies = list?.results
        movies?.forEach { movie ->
            movie.poster_path = movie.poster_path?.getFullImageUrl()
            movie.backdrop_path = movie.backdrop_path?.getFullImageUrl()

        }
        return movies ?: listOf()
    }

}


