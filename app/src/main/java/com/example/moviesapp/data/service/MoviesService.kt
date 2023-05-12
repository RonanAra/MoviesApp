package com.example.moviesapp.data.service

import android.app.SearchManager
import com.example.moviesapp.data.model.MovieResult
import com.example.moviesapp.data.model.MovieSimilarResults
import com.example.moviesapp.data.model.MovieTopRated
import com.example.moviesapp.data.model.RecommendResult
import retrofit2.http.*

interface MoviesService {

    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("page") page: Int
    ): MovieResult

    @GET("search/movie")
    suspend fun getSearchMovie(
        @Query(SearchManager.QUERY) searchMovie: String
    ): MovieResult

    @GET("movie/upcoming")
    suspend fun getRecommended(
        @Query("page") page: Int
    ): RecommendResult

    @GET("movie/{movie_id}/similar")
    suspend fun getSimilar(
        @Path("movie_id") movieId: Int
    ): MovieSimilarResults

    @GET("movie/top_rated")
    suspend fun getTopRated(
        @Query("page") page: Int
    ): MovieTopRated
}