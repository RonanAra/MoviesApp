package com.example.moviesapp.api


import com.example.moviesapp.model.Popular
import retrofit2.Response
import retrofit2.http.*

interface TmdbApi {

    @GET("movie/popular")
    suspend fun getPopularMovies(): Response<Popular>


}