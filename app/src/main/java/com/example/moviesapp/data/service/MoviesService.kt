package com.example.moviesapp.data.service

import android.app.SearchManager
import com.example.moviesapp.data.models.ListMoviesResponse
import retrofit2.http.*

interface MoviesService {

    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("page") page: Int
    ): ListMoviesResponse

    @GET("search/movie")
    suspend fun getSearchMovie(
        @Query(SearchManager.QUERY) searchMovie: String
    ): ListMoviesResponse

    @GET("movie/upcoming")
    suspend fun getRecommended(
        @Query("page") page: Int
    ): ListMoviesResponse

    @GET("movie/{movie_id}/similar")
    suspend fun getSimilar(
        @Path("movie_id") movieId: Int
    ): ListMoviesResponse

    @GET("movie/top_rated")
    suspend fun getTopRated(
        @Query("page") page: Int
    ): ListMoviesResponse
}