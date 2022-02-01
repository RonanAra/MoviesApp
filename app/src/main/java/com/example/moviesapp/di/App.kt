package com.example.moviesapp.di

import android.app.Application
import com.example.moviesapp.di.AppModule.appModule
import com.example.moviesapp.di.AppModule.databaseModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application(){
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(
                appModule,
                databaseModule
            )
        }
    }
}