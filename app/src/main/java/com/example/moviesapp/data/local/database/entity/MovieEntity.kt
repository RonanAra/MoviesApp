package com.example.moviesapp.data.local.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

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