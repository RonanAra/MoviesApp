package com.example.moviesapp.di

import com.example.moviesapp.domain.homerepository.HomeRepository
import com.example.moviesapp.domain.homeusecase.HomeUseCase
import com.example.moviesapp.presentation.paging.HomePagingSource
import com.example.moviesapp.presentation.viewmodel.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object AppModule {
    val appModule = module {
        single { HomeRepository() }


        single { HomeUseCase(repository = get()) }


        single {
            HomePagingSource(
                homeRepository = get(),
                homeUseCase = get()
            )
        }


        viewModel { HomeViewModel(homeUseCase = get(), homeRepository = get()) }

    }
}
