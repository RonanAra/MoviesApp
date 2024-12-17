package com.example.moviesapp.data.mapper

import com.example.moviesapp.data.models.MovieItemResponse
import com.example.moviesapp.domain.models.MoviesModel

fun List<MovieItemResponse>.toModel(): List<MoviesModel> {
    return this.map { movie ->
        with(movie) {
            MoviesModel(
                id = id,
                backdropPath = backdropPath,
                overview = overview,
                posterPath = posterPath,
                title = title,
                voteAverage = voteAverage,
                voteCount = voteCount,
                popularity = popularity
            )
        }
    }
}