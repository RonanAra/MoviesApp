package com.example.moviesapp.presentation.home.viewmodel

import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.moviesapp.data.repository.api.ApiRepository
import com.example.moviesapp.presentation.base.BaseViewModel
import com.example.moviesapp.presentation.home.paging.HomePagingSource
import com.example.moviesapp.domain.usecase.HomeUseCase
import com.example.moviesapp.data.model.Movie
import com.example.moviesapp.presentation.home.paging.RecommendPagingSource
import com.example.moviesapp.presentation.home.paging.TopRatedPagingSource
import kotlinx.coroutines.flow.Flow

class HomeViewModel(
    private val repository: ApiRepository,
    private val homeUseCase: HomeUseCase,
) : BaseViewModel() {


    private var popularPagingData: Flow<PagingData<Movie>>? = null;
    private var topPagingData: Flow<PagingData<Movie>>? = null;
    private var recommendPagingData: Flow<PagingData<Movie>>? = null;

    fun getPopular(): Flow<PagingData<Movie>> {
        if (popularPagingData != null) return popularPagingData as Flow<PagingData<Movie>>
        else
            popularPagingData = Pager(config = PagingConfig(pageSize = 20),
                pagingSourceFactory = {
                    HomePagingSource(
                        repository,
                        homeUseCase
                    )
                }).flow.cachedIn(
                viewModelScope
            )
        return popularPagingData as Flow<PagingData<Movie>>
    }

    fun getRecommend(): Flow<PagingData<Movie>> {
        if (recommendPagingData != null) return recommendPagingData as Flow<PagingData<Movie>>
        else
            recommendPagingData = Pager(config = PagingConfig(pageSize = 20),
                pagingSourceFactory = {
                    RecommendPagingSource(
                        repository,
                        homeUseCase
                    )
                }).flow.cachedIn(
                viewModelScope
            )
        return recommendPagingData as Flow<PagingData<Movie>>
    }

    fun getTopRated(): Flow<PagingData<Movie>> {
        if (topPagingData != null) return topPagingData as Flow<PagingData<Movie>>
        else
            topPagingData = Pager(config = PagingConfig(pageSize = 20),
                pagingSourceFactory = {
                    TopRatedPagingSource(
                        repository,
                        homeUseCase
                    )
                }).flow.cachedIn(
                viewModelScope
            )
        return topPagingData as Flow<PagingData<Movie>>
    }
}



