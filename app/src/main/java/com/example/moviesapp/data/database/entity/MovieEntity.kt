package com.example.moviesapp.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.moviesapp.data.model.Movie

@Entity(tableName = "moviesDb")
data class MovieEntity(
    @PrimaryKey
    val id: Int? = null,
    @ColumnInfo(name = "backdropPath")
    var backdrop_path: String? = null,
    @ColumnInfo(name = "overview")
    val overview: String? = null,
    @ColumnInfo(name = "posterPath")
    var poster_path: String? = null,
    @ColumnInfo(name = "title")
    val title: String? = null
)


fun toMovies(listMovies: List<MovieEntity>) : ArrayList<Movie> {
    val listMovieFavorites = ArrayList<Movie>()
    for (movieFavoriteItem in listMovies) {
        val movieFavorite = Movie(
            movieFavoriteItem.id,
            movieFavoriteItem.title,
            movieFavoriteItem.poster_path,
            movieFavoriteItem.overview,
            movieFavoriteItem.backdrop_path,
        )
        listMovieFavorites.add(movieFavorite)
    }
    return listMovieFavorites
}