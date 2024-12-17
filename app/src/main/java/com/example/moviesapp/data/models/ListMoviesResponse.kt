package com.example.moviesapp.data.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ListMoviesResponse(
    val page: Int,
    val results: List<MoviesResponse>
) : Serializable

data class MoviesResponse(
    val id: Int,
    @SerializedName("backdrop_path") var backdropPath: String,
    val overview: String,
    @SerializedName("poster_path") var posterPath: String,
    val title: String,
    @SerializedName("vote_average") val voteAverage: Double,
    @SerializedName("vote_count") val voteCount: Int,
    val popularity: Double
)