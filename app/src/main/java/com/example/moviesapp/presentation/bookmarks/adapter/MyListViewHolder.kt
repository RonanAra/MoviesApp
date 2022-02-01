package com.alvarengadev.alvaflix.view.mylist.adapter

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesapp.presentation.interfaces.MovieOnClickListener
import com.bumptech.glide.Glide
import com.example.moviesapp.R
import com.example.moviesapp.data.model.Movie

class MyListViewHolder(
    itemView: View,
    onClickListener: MovieOnClickListener,
    listMovieFavorites: ArrayList<Movie>
) : RecyclerView.ViewHolder(itemView) {

    init {
        itemView.setOnClickListener {
            val positionRcy = position
            if (positionRcy != RecyclerView.NO_POSITION) {
                onClickListener.onItemClick(listMovieFavorites[positionRcy])
            }
        }
    }

    fun bind(movieFavorite: Movie) {
        val ivPoster = itemView.findViewById(R.id.iv_movie_poster) as ImageView

        Glide
            .with(itemView.context)
            .load("https://image.tmdb.org/t/p/w500/${movieFavorite.poster_path}")
            .into(ivPoster)

    }

}