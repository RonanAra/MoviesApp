package com.example.moviesapp.data.model

data class NowPlaying(
    val page: Int,
    val results: List<Movie>,
    val total_pages: Int,
    val total_results: Int
)