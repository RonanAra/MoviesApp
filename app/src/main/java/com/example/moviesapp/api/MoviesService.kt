package com.example.moviesapp.api


import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object MoviesService {

    val tmdbApi: TmdbApi = getTmdbApiClient().create(TmdbApi::class.java)

    fun getTmdbApiClient(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}