package com.example.moviesapp.common.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.moviesapp.data.models.MovieItemResponse
import com.example.moviesapp.data.models.MoviesResponse
import com.example.moviesapp.domain.entity.MoviesModel

class MoviesPagingSource(
    private val mapper: (List<MovieItemResponse>) -> List<MoviesModel>,
    private val fetchMovies: suspend (Int) -> MoviesResponse
) : PagingSource<Int, MoviesModel>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MoviesModel> {
        return try {
            val page = params.key ?: 1
            val response = fetchMovies(page)
            val nextKey = if (response.page < response.totalPages) page.plus(1) else null

            LoadResult.Page(
                data = mapper.invoke(response.results),
                prevKey = if (page == 1) null else page - 1,
                nextKey = nextKey
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, MoviesModel>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}