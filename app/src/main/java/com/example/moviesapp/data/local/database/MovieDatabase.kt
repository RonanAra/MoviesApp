package com.example.moviesapp.data.local.database

import androidx.room.*
import androidx.room.migration.AutoMigrationSpec
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.moviesapp.data.local.database.dao.MovieFavoritesDao
import com.example.moviesapp.data.local.database.entity.MovieEntity

@Database(
    version = 2,
    entities = [MovieEntity::class],
    autoMigrations = [AutoMigration(from = 1, to = 2, spec = MovieDatabase.MyExampleAutoMigration::class)],
    exportSchema = true
)
abstract class MovieDatabase : RoomDatabase() {
    abstract val movieFavoritesDao: MovieFavoritesDao

    @DeleteColumn(tableName = "moviesDb", columnName = "voteAverage")
    class MyExampleAutoMigration : AutoMigrationSpec {}
}
