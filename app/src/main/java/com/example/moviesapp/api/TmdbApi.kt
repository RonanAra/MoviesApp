package com.example.moviesapp.api


import com.example.moviesapp.model.Popular
import retrofit2.Response
import retrofit2.http.*

interface TmdbApi {

    @GET("movie/popular?api_key=50bdd2252f94607d034b1105ddca8550")
    suspend fun getPopularMovies(): Response<Popular>

}