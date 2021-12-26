package com.example.moviesapp.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.moviesapp.domain.homerepository.HomeRepository
import com.example.moviesapp.presentation.base.BaseViewModel
import com.example.moviesapp.presentation.paging.HomePagingSource
import com.example.moviesapp.domain.homeusecase.HomeUseCase
import com.example.moviesapp.data.model.Result
import kotlinx.coroutines.flow.Flow

class HomeViewModel(
    private val homeRepository: HomeRepository,
    private val homeUseCase: HomeUseCase
) : BaseViewModel() {

    private var mPagingData : Flow<PagingData<Result>>? = null;

    fun getPopular(): Flow<PagingData<Result>> {
        if(mPagingData != null) return mPagingData as Flow<PagingData<Result>>
        else
            mPagingData = Pager(config = PagingConfig(pageSize = 20),
                pagingSourceFactory = { HomePagingSource(homeRepository, homeUseCase) }).flow.cachedIn(
                viewModelScope)
        return mPagingData as Flow<PagingData<Result>>
    }

}

