package com.example.moviesapp.domain.usecase

import com.example.moviesapp.data.model.Popular
import com.example.moviesapp.data.model.Result
import com.example.moviesapp.domain.repository.DetailsRepository
import com.example.moviesapp.extensions.getFullImageUrl
import com.example.moviesapp.utils.ResponseApi

class DetailsUseCase constructor(
    private val repository: DetailsRepository
) {
    suspend fun getMovieById(movieId: Int): ResponseApi {
        return when (val responseApi = repository.getMovieById(movieId)) {
            is ResponseApi.Success -> {
                val movie = responseApi.data as? Result
                movie?.backdrop_path = movie?.backdrop_path?.getFullImageUrl().toString()
                ResponseApi.Success(movie)
            }
            is ResponseApi.Error -> {
                responseApi
            }
        }
    }

}

