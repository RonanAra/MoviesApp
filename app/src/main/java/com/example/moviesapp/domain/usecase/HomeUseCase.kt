package com.example.moviesapp.domain.usecase

import com.example.moviesapp.extensions.getFullImageUrl
import com.example.moviesapp.data.repository.api.HomeApiRepository
import com.example.moviesapp.data.model.MovieResult
import com.example.moviesapp.data.model.Movie
import com.example.moviesapp.data.model.MovieTopRated
import com.example.moviesapp.data.model.RecommendResult
import com.example.moviesapp.utils.ConstantsApp.Api.FIRST_PAGE
import com.example.moviesapp.utils.ResponseApi

class HomeUseCase(
    private val repository: HomeApiRepository
) {


    fun setupMoviesList(list: MovieResult?): List<Movie> {
        val movies = list?.results
        movies?.forEach { movie ->
            movie.poster_path = movie.poster_path?.getFullImageUrl()
            movie.backdrop_path = movie.backdrop_path?.getFullImageUrl()

        }
        return movies ?: listOf()
    }

    suspend fun getRecommend(): ResponseApi {
        return when (val responseApi = repository.getRecommend()) {
            is ResponseApi.Success -> {
                val data = responseApi.data as? RecommendResult
                val result = data?.results?.map {
                    it.backdrop_path = it.backdrop_path?.getFullImageUrl()
                    it.poster_path = it.poster_path?.getFullImageUrl()
                    it
                }
                ResponseApi.Success(result)
            }
            is ResponseApi.Error -> {
                responseApi
            }
        }
    }

    suspend fun getTopRated(): ResponseApi {
        return when (val responseApi = repository.getTopRated()) {
            is ResponseApi.Success -> {
                val data = responseApi.data as? MovieTopRated
                val result = data?.results?.map {
                    it.backdrop_path = it.backdrop_path?.getFullImageUrl()
                    it.poster_path = it.poster_path?.getFullImageUrl()
                    it
                }
                ResponseApi.Success(result)
            }
            is ResponseApi.Error -> {
                responseApi
            }
        }
    }

}




