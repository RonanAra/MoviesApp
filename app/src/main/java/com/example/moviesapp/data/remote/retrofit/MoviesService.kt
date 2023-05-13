package com.example.moviesapp.data.remote.retrofit

import androidx.viewbinding.BuildConfig
import com.example.moviesapp.data.remote.MoviesService
import com.example.moviesapp.utils.ConstantsApp.Api.API_TOKEN
import com.example.moviesapp.utils.ConstantsApp.Api.API_TOKEN_KEY
import com.example.moviesapp.utils.ConstantsApp.Api.BASE_URL
import com.example.moviesapp.utils.ConstantsApp.Api.QUERY_PARAM_LANGUAGE_KEY
import com.example.moviesapp.utils.ConstantsApp.Api.QUERY_PARAM_LANGUAGE_VALUE
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object MoviesService {

    val moviesService: MoviesService = getTmdbApiClient().create(MoviesService::class.java)

    fun getTmdbApiClient(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(getInterceptorClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun getInterceptorClient(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        if (BuildConfig.DEBUG) {
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        }

        val interceptor = OkHttpClient.Builder()
            .connectTimeout(5, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)
            .addInterceptor { chain ->
                val url = chain.request().url.newBuilder()
                    .addQueryParameter(API_TOKEN_KEY, API_TOKEN)
                    .addQueryParameter(QUERY_PARAM_LANGUAGE_KEY, QUERY_PARAM_LANGUAGE_VALUE)
                    .build()
                val newRequest = chain.request().newBuilder().url(url).build()
                chain.proceed(newRequest)
            }
        return interceptor.build()
    }

}
