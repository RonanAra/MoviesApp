package com.example.moviesapp.presentation.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.moviesapp.R
import com.example.moviesapp.databinding.ItemListHorizontalMoviesBinding
import com.example.moviesapp.domain.entity.MoviesModel
import com.example.moviesapp.utils.extensions.getFullImageUrl

class MoviesListVIewHolder(
    private val onItemClickListener: (MoviesModel) -> Unit,
    private val itemBinding: ItemListHorizontalMoviesBinding
) : ViewHolder(itemBinding.root) {

    fun bind(movie: MoviesModel) = with(itemBinding) {

        Glide.with(itemView)
            .load(movie.backdropPath.getFullImageUrl())
            .fallback(R.drawable.place_holder)
            .into(imgMoviePoster)

        movieItem.setOnClickListener {
            onItemClickListener(movie)
        }
    }

    companion object {
        fun create(
            parent: ViewGroup,
            onItemClickListener: (MoviesModel) -> Unit
        ): MoviesListVIewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val itemBinding = ItemListHorizontalMoviesBinding.inflate(
                inflater,
                parent,
                false
            )
            return MoviesListVIewHolder(
                itemBinding = itemBinding,
                onItemClickListener = onItemClickListener
            )
        }
    }
}