package com.alvarengadev.alvaflix.view.interfaces

import com.example.moviesapp.data.model.Movie

interface MovieOnClickListener {
    fun onItemClick(movie: Movie)
}