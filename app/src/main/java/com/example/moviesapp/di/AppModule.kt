package com.example.moviesapp.di

import com.example.moviesapp.data.repository.api.DetailsRepository
import com.example.moviesapp.data.repository.api.HomeRepository
import com.example.moviesapp.domain.usecase.DetailsUseCase
import com.example.moviesapp.domain.usecase.HomeUseCase
import com.example.moviesapp.presentation.home.paging.HomePagingSource
import com.example.moviesapp.presentation.home.viewmodel.HomeViewModel
import com.example.moviesapp.presentation.moviedetails.viewmodel.DetailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object AppModule {
    val appModule = module {
        single { HomeRepository() }
        factory { DetailsRepository() }



        single { HomeUseCase(repository = get()) }
        single { DetailsUseCase(repository = get()) }

        single {
            HomePagingSource(
                homeRepository = get(),
                homeUseCase = get()
            )
        }

        viewModel { DetailsViewModel(detailsUseCase = get()) }
        viewModel { HomeViewModel(homeUseCase = get(), homeRepository = get()) }

    }
}
