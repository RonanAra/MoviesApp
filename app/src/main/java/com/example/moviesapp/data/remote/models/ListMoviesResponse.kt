package com.example.moviesapp.data.remote.models

import com.example.moviesapp.data.local.models.MoviesModel
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ListMoviesResponse(
    val page: Int,
    val results: List<MoviesResponse>
) : Serializable

data class MoviesResponse(
    val id: Int,
    @SerializedName("backdrop_path")
    var backdropPath: String,
    val overview: String,
    @SerializedName("poster_path")
    var posterPath: String,
    val title: String,
    @SerializedName("vote_average")
    val voteAverage: Double,
    @SerializedName("vote_count")
    val voteCount: Int,
    val popularity: Double
) {
    fun toMoviesModel(): MoviesModel {
        return MoviesModel(
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