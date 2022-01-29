package com.example.moviesapp.domain.usecase

import com.example.moviesapp.data.model.Movie
import com.example.moviesapp.data.model.MovieResult
import com.example.moviesapp.data.repository.api.SearchRepository
import com.example.moviesapp.extensions.getFullImageUrl
import com.example.moviesapp.utils.ResponseApi

class SearchUseCase(
    private val searchRepository: SearchRepository
) {

    suspend fun getSearch(titleMovie: String): ResponseApi {
        return when (val responseApi = searchRepository.getSearch(titleMovie)) {
            is ResponseApi.Success -> {
                val data = responseApi.data as? MovieResult
                val result = data?.results?.map {
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