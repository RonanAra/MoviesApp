package com.example.moviesapp.di

import android.provider.SyncStateContract
import androidx.room.Room
import com.example.moviesapp.data.database.MovieDatabase
import com.example.moviesapp.data.database.dao.MovieFavoritesDao
import com.example.moviesapp.data.repository.api.DetailsApiRepository
import com.example.moviesapp.data.repository.api.HomeApiRepository
import com.example.moviesapp.data.repository.api.SearchApiRepository
import com.example.moviesapp.data.repository.database.DetailsDaoRepository
import com.example.moviesapp.domain.usecase.DetailsUseCase
import com.example.moviesapp.domain.usecase.HomeUseCase
import com.example.moviesapp.domain.usecase.SearchUseCase
import com.example.moviesapp.presentation.home.paging.HomePagingSource
import com.example.moviesapp.presentation.home.viewmodel.HomeViewModel
import com.example.moviesapp.presentation.moviedetails.viewmodel.DetailsViewModel
import com.example.moviesapp.presentation.search.viewmodel.SearchViewModel
import com.example.moviesapp.utils.ConstantsApp
import com.example.moviesapp.utils.ConstantsApp.Database.NAME_DATABASE
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.scope.get
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
        factory { DetailsApiRepository() }
        single { SearchApiRepository() }


        factory { DetailsDaoRepository(movieFavoritesDao = get()) }


        single { HomeUseCase(repository = get()) }
        single { DetailsUseCase(repository = get()) }
        single { SearchUseCase(searchRepository = get()) }

        single {
            HomePagingSource(
                homeRepository = get(),
                homeUseCase = get()
            )
        }

        viewModel { DetailsViewModel(detailsUseCase = get(), detailsDaoRepository = get()) }
        viewModel { HomeViewModel(homeUseCase = get(), homeRepository = get()) }
        viewModel { SearchViewModel(searchUseCase = get())}
    }
}
