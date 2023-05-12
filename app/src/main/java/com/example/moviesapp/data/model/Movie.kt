package com.example.moviesapp.data.model

import com.example.moviesapp.data.database.entity.MovieEntity
import java.io.Serializable

data class Movie(
    val id: Int,
    var backdrop_path: String,
    val overview: String,
    var poster_path: String,
    val title: String,
    val vote_average: String,
    val vote_count: Int,
    val popularity: Double,
    val original_language: String
) : Serializable

fun toEntity(movie: Movie): MovieEntity {
    return MovieEntity(
        id = movie.id,
        title = movie.title,
        poster_path = movie.poster_path,
        overview = movie.overview,
        backdrop_path = movie.backdrop_path,
        vote_average = movie.vote_average
    )
}