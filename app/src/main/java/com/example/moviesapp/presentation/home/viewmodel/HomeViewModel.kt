package com.example.moviesapp.presentation.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.moviesapp.data.repository.api.HomeApiRepository
import com.example.moviesapp.presentation.base.BaseViewModel
import com.example.moviesapp.presentation.home.paging.HomePagingSource
import com.example.moviesapp.domain.usecase.HomeUseCase
import com.example.moviesapp.data.model.Movie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val homeRepository: HomeApiRepository,
    private val homeUseCase: HomeUseCase,
) : BaseViewModel() {

    private val _onSuccesLatest: MutableLiveData<List<Movie>> = MutableLiveData()

    val onSuccessLatest: LiveData<List<Movie>>
        get() = _onSuccesLatest


    private var mPagingData: Flow<PagingData<Movie>>? = null;

    fun getPopular(): Flow<PagingData<Movie>> {
        if (mPagingData != null) return mPagingData as Flow<PagingData<Movie>>
        else
            mPagingData = Pager(config = PagingConfig(pageSize = 20),
                pagingSourceFactory = {
                    HomePagingSource(
                        homeRepository,
                        homeUseCase
                    )
                }).flow.cachedIn(
                viewModelScope
            )
        return mPagingData as Flow<PagingData<Movie>>
    }

    fun getLatestMovies(page: Int) {
        viewModelScope.launch {
            callApi(
                suspend { homeUseCase.getNowPlayingMovies(page) },
                onSuccess = {
                    val result = it as? List<*>
                    _onSuccesLatest.postValue(
                        result?.filterIsInstance<Movie>()
                    )
                })
        }
    }
}



