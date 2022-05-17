package com.example.moviesapp.data.api


import android.app.SearchManager
import com.example.moviesapp.data.model.MovieResult
import com.example.moviesapp.data.model.MovieSimilarResults
import com.example.moviesapp.data.model.MovieTopRated
import com.example.moviesapp.data.model.RecommendResult
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface TmdbApi {

    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("page") page: Int
    ): Response<MovieResult>

    @GET("search/movie")
    suspend fun getSearchMovie(
        @Query(SearchManager.QUERY) searchMovie: String
    ): Response<MovieResult>

    @GET("movie/upcoming")
    suspend fun listRecommended(): Response<RecommendResult>

    @GET("movie/{movie_id}/similar")
    suspend fun listSimilar(
        @Path("movie_id") movieId: Int
    ): Response<MovieSimilarResults>


    @GET("movie/top_rated")
    suspend fun listTopRated(): Response<MovieTopRated>

}