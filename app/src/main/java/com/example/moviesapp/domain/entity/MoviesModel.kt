package com.example.moviesapp.domain.entity

import java.io.Serializable

data class MoviesModel(
    val id: Int,
    var backdropPath: String,
    val overview: String,
    var posterPath: String,
    val title: String,
    val voteAverage: Double,
    val voteCount: Int,
    val popularity: Double
) : Serializable