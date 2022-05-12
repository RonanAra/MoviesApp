package com.example.moviesapp.domain.usecase

import com.example.moviesapp.data.model.MovieResult
import com.example.moviesapp.data.model.MovieSimilarResults
import com.example.moviesapp.data.repository.api.DetailsApiRepository
import com.example.moviesapp.data.repository.api.SearchApiRepository
import com.example.moviesapp.extensions.getFullImageUrl
import com.example.moviesapp.utils.ResponseApi

class DetailsUseCase(
    private val similarRepository: DetailsApiRepository
) {

    suspend fun getSimilar(movieId: Int): ResponseApi {
        return when (val responseApi = similarRepository.getSimilar(movieId)) {
            is ResponseApi.Success -> {
                val data = responseApi.data as? MovieSimilarResults
                val result = data?.results?.map {
                    it.poster_path = it.poster_path?.getFullImageUrl()
                    it.backdrop_path = it.backdrop_path?.getFullImageUrl()
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