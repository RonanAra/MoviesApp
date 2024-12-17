package com.example.moviesapp.data.service

import android.app.SearchManager
import com.example.moviesapp.data.models.MoviesResponse
import retrofit2.http.*

interface MoviesService {

    @GET("movie/popular")
    suspend fun getPopularMovies(@Query("page") page: Int): MoviesResponse

    @GET("search/movie")
    suspend fun getSearchMovies(@Query(SearchManager.QUERY) searchMovie: String): MoviesResponse

    @GET("movie/upcoming")
    suspend fun getRecommendedMovies(@Query("page") page: Int): MoviesResponse

    @GET("movie/{movie_id}/similar")
    suspend fun getSimilarMovies(@Path("movie_id") movieId: Int): MoviesResponse

    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(@Query("page") page: Int): MoviesResponse
}