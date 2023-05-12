package com.example.moviesapp.data.model

import java.io.Serializable

data class MovieResult(
    val results: List<Movie>
) : Serializable