package com.example.moviesapp.data.repository.database

import com.example.moviesapp.data.database.dao.MovieFavoritesDao
import com.example.moviesapp.data.database.entity.toMovies
import com.example.moviesapp.data.model.Movie
import com.example.moviesapp.data.model.toEntity

class MovieDaoRepositoryImp(
    private val movieFavoritesDao: MovieFavoritesDao
) {

    suspend fun insert(movie: Movie) {
        movieFavoritesDao.insert(toEntity(movie))
    }

    suspend fun delete(movie: Movie) {
        movieFavoritesDao.delete(toEntity(movie))
    }

    suspend fun getAllMovieFavorites(): ArrayList<Movie> {
        return toMovies(movieFavoritesDao.getAll())
    }

}