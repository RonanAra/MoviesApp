package com.example.moviesapp.presentation.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.moviesapp.domain.homerepository.HomeRepository
import com.example.moviesapp.domain.homeusecase.HomeUseCase
import com.example.moviesapp.data.model.Popular
import com.example.moviesapp.data.model.Result
import com.example.moviesapp.utils.ResponseApi

class HomePagingSource(
    private val homeRepository: HomeRepository,
    private val homeUseCase: HomeUseCase
) : PagingSource<Int, Result>() {

    override fun getRefreshKey(state: PagingState<Int, Result>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Result> {

        return try {
            val page: Int = params.key ?: 1
            val response = callPopularApi(page)

            LoadResult.Page(
                data = response,
                prevKey = if (page == 1) null else -1,
                nextKey = page.plus(1)
            )

        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    private suspend fun callPopularApi(page: Int): List<Result> {
        return when (
            val response = homeRepository.getPopular(page)
        ) {
            is ResponseApi.Success -> {
                val list = response.data as? Popular
                homeUseCase.setupMoviesList(list)
            }
            is ResponseApi.Error -> {
                listOf()
            }
        }
    }
}