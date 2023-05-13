package com.example.moviesapp.data.local.database.dao

import androidx.room.*
import com.example.moviesapp.data.local.database.entity.MovieEntity
import kotlin.coroutines.Continuation

@Dao
interface MovieFavoritesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(movieEntity: MovieEntity)

    @Delete
    suspend fun delete(movieEntity: MovieEntity)

    @Query("SELECT * FROM moviesDb")
    suspend fun getAll(): List<MovieEntity>
}