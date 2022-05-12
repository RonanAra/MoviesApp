package com.example.moviesapp.di

import androidx.room.Room
import com.example.moviesapp.data.database.MovieDatabase
import com.example.moviesapp.data.repository.api.DetailsApiRepository
import com.example.moviesapp.data.repository.api.HomeApiRepository
import com.example.moviesapp.data.repository.api.SearchApiRepository
import com.example.moviesapp.data.repository.database.MovieDaoRepositoryImp
import com.example.moviesapp.domain.usecase.DetailsUseCase
import com.example.moviesapp.domain.usecase.HomeUseCase
import com.example.moviesapp.domain.usecase.SearchUseCase
import com.example.moviesapp.presentation.bookmarks.viewmodel.BookViewModel
import com.example.moviesapp.presentation.home.paging.HomePagingSource
import com.example.moviesapp.presentation.home.viewmodel.HomeViewModel
import com.example.moviesapp.presentation.moviedetails.viewmodel.DetailsViewModel
import com.example.moviesapp.presentation.search.viewmodel.SearchViewModel
import com.example.moviesapp.utils.ConstantsApp
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object AppModule {

    val databaseModule = module {
        single {
            Room.databaseBuilder(
                get(),
                MovieDatabase::class.java,
                ConstantsApp.Database.NAME_DATABASE
            ).build()
        }
        single { get<MovieDatabase>().movieFavoritesDao }
    }


    val appModule = module {
        single { HomeApiRepository() }
        single { SearchApiRepository() }
        single { DetailsApiRepository() }


        factory { MovieDaoRepositoryImp(movieFavoritesDao = get()) }


        single { HomeUseCase(repository = get()) }
        single { SearchUseCase(searchRepository = get()) }
        single { DetailsUseCase(similarRepository = get()) }

        single {
            HomePagingSource(
                homeRepository = get(),
                homeUseCase = get()
            )
        }

        viewModel { BookViewModel(movieDaoRepository = get()) }
        viewModel { DetailsViewModel(detailsDaoRepository = get(), similarUseCase = get()) }
        viewModel { HomeViewModel(homeUseCase = get(), homeRepository = get()) }
        viewModel { SearchViewModel(searchUseCase = get())}
    }
}
