package com.example.moviesapp.model

data class NowPlaying(
    val dates: Dates,
    val page: Int,
    val results: List<ResultX>,
    val total_pages: Int,
    val total_results: Int
)