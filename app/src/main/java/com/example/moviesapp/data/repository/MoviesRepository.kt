package com.example.moviesapp.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.moviesapp.common.paging.MoviesPagingSource
import com.example.moviesapp.domain.entity.MoviesModel
import com.example.moviesapp.data.datasource.MoviesDataSource
import com.example.moviesapp.data.mapper.mapperToMovieModel
import com.example.moviesapp.data.models.MoviesResponse
import com.example.moviesapp.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor(
    private val moviesDataSource: MoviesDataSource
) : MoviesRepository {
    override suspend fun getPopular(): Flow<PagingData<MoviesModel>> {
        return createPager { page -> moviesDataSource.getPopular(page) }
    }

    override suspend fun getRecommend(): Flow<PagingData<MoviesModel>> {
        return createPager { page -> moviesDataSource.getRecommend(page) }
    }

    override suspend fun getTopRated(): Flow<PagingData<MoviesModel>> {
        return createPager { page -> moviesDataSource.getTopRated(page) }
    }

    override suspend fun getSimilar(movieId: Int): List<MoviesModel> {
        return moviesDataSource.getSimilar(movieId).results.mapperToMovieModel()
    }

    override suspend fun search(titleMovie: String): List<MoviesModel> {
        return moviesDataSource.search(titleMovie).results.mapperToMovieModel()
    }

    private fun createPager(fetchMovies: suspend (Int) -> MoviesResponse): Flow<PagingData<MoviesModel>> {
        return Pager(
            config = PAGING_CONFIG,
            pagingSourceFactory = {
                MoviesPagingSource(
                    fetchMovies = fetchMovies,
                    mapper = { response -> response.mapperToMovieModel() }
                )
            }
        ).flow
    }

    companion object {
        private val PAGING_CONFIG = PagingConfig(pageSize = 20)
    }
}