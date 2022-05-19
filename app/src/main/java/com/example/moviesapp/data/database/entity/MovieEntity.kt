package com.example.moviesapp.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.moviesapp.data.model.Movie

@Entity(tableName = "moviesDb")
data class MovieEntity(
    @PrimaryKey
    val id: Int?,
    @ColumnInfo(name = "backdropPath")
    var backdrop_path: String?,
    @ColumnInfo(name = "overview")
    val overview: String?,
    @ColumnInfo(name = "posterPath")
    var poster_path: String?,
    @ColumnInfo(name = "title")
    val title: String?,
    @ColumnInfo(name = "vote_average")
    val vote_average: String?
)


fun toMovies(listMovies: List<MovieEntity>) : ArrayList<Movie> {
    val listMovieFavorites = ArrayList<Movie>()
    for (movieFavoriteItem in listMovies) {
        val movieFavorite = Movie(
            movieFavoriteItem.id,
            movieFavoriteItem.backdrop_path,
            movieFavoriteItem.overview,
            movieFavoriteItem.poster_path,
            movieFavoriteItem.title,
            movieFavoriteItem.vote_average
        )
        listMovieFavorites.add(movieFavorite)
    }
    return listMovieFavorites
}