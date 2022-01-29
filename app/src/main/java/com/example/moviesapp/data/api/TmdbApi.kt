package com.example.moviesapp.data.api


import android.app.SearchManager
import com.example.moviesapp.data.model.MovieResult
import com.example.moviesapp.data.model.Movie
import retrofit2.Response
import retrofit2.http.*

interface TmdbApi {

    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("page") page: Int
    ): Response<MovieResult>

    @GET("movie/{movie_id}")
    suspend fun getMovieById(
        @Path("movie_id") movieId: Int
    ): Response<Movie>

    @GET("search/movie")
    suspend fun getSearchMovie(
        @Query(SearchManager.QUERY) searchMovie: String
    ): Response<MovieResult>

}