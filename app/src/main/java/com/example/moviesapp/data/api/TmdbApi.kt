package com.example.moviesapp.data.api


import com.example.moviesapp.data.model.Popular
import retrofit2.Response
import retrofit2.http.*

interface TmdbApi {

    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("page") page: Int
    ): Response<Popular>


}