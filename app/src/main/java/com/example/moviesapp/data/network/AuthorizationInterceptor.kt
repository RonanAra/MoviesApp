package com.example.moviesapp.data.network

import okhttp3.Interceptor
import okhttp3.Response

class AuthorizationInterceptor(
    private val apiKey: String
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val requestUrl = request.url

        val newUrl = requestUrl.newBuilder()
            .addQueryParameter(API_KEY, apiKey)
            .build()

        return chain.proceed(
            request.newBuilder()
                .addHeader("accept", "application/json")
                .addHeader("Authorization",
                    "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI1MGJkZDIyNTJmOTQ2MDdkMDM0YjExMDVkZGNhODU1MCIsIm5iZiI6M" +
                            "TYyODcwODUxMi44ODgsInN1YiI6IjYxMTQxZWEwMzUwMzk4MDAyZGIwYmM0NiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.IdVfwrMlPjZpRua" +
                            "szSsMxaB3IgL0K-B-FVh_d17x0yU"
                )
                .url(newUrl)
                .build()
        )
    }

    companion object {
        private const val API_KEY = "apikey"
    }
}