package com.example.moviesapp.features.usecase

import com.example.moviesapp.extensions.getFullImageUrl
import com.example.moviesapp.api.HomeRepository
import com.example.moviesapp.model.Popular
import com.example.moviesapp.utils.ResponseApi

class HomeUseCase {

    private val homeRepository = HomeRepository()

    suspend fun getPopular(): ResponseApi {
        return when (val responseApi = homeRepository.getPopular()) {
            is ResponseApi.Success -> {
                var data = responseApi.data as? Popular
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


