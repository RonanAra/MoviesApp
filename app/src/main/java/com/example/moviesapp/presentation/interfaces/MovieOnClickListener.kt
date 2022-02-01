package com.example.moviesapp.presentation.interfaces

import com.example.moviesapp.data.model.Movie

interface MovieOnClickListener {
    fun onItemClick(movie: Movie)
}