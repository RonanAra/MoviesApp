package com.example.moviesapp.presentation.home.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.moviesapp.data.repository.api.ApiRepository
import com.example.moviesapp.domain.usecase.HomeUseCase
import com.example.moviesapp.data.model.Movie
import com.example.moviesapp.data.model.MovieTopRated
import com.example.moviesapp.utils.ResponseApi

class TopRatedPagingSource(
    private val repository: ApiRepository,
    private val homeUseCase: HomeUseCase
) : PagingSource<Int, Movie>() {

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {

        return try {
            val page: Int = params.key ?: 1
            val response = callTopRatedApi(page)

            LoadResult.Page(
                data = response,
                prevKey = if (page == 1) null else -1,
                nextKey = page.plus(1)
            )

        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }


    private suspend fun callTopRatedApi(page: Int): List<Movie> {
        return when (
            val response = repository.getTopRated(page)
        ) {
            is ResponseApi.Success -> {
                val list = response.data as? MovieTopRated
                homeUseCase.setupTopRated(list)
            }
            is ResponseApi.Error -> {
                listOf()
            }
        }
    }

}