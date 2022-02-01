package com.example.moviesapp.data.database.dao

import androidx.room.*
import com.example.moviesapp.data.database.entity.MovieEntity

@Dao
interface MovieFavoritesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(movieEntity: MovieEntity)

    @Delete
    suspend fun delete(movieEntity: MovieEntity)

    @Query("SELECT * FROM moviesDb")
    suspend fun getAll(): List<MovieEntity>
}