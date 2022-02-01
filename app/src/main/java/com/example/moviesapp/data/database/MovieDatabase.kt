package com.example.moviesapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.moviesapp.data.database.dao.MovieFavoritesDao
import com.example.moviesapp.data.database.entity.MovieEntity

@Database(entities = [MovieEntity::class], version = 1, exportSchema = false)
abstract class MovieDatabase : RoomDatabase() {
    abstract val movieFavoritesDao: MovieFavoritesDao
}