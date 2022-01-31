package com.example.moviesapp.presentation.search.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviesapp.data.model.Movie
import com.example.moviesapp.databinding.ItemSearchMoviesBinding

class SearchAdapter(
    private val onClickListener: (movie: Movie) -> Unit
) : ListAdapter<Movie, SearchAdapter.SerieViewHolder>(Movie.DIFF_CALLBACK) {

    inner class SerieViewHolder(val binding: ItemSearchMoviesBinding) :
        RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SerieViewHolder {
        return SerieViewHolder(
            ItemSearchMoviesBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: SerieViewHolder, position: Int) {
        val movieCurrent = getItem(position)
        Glide
            .with(holder.binding.ivMoviePosterSearch)
            .load(movieCurrent.poster_path)
            .into(holder.binding.ivMoviePosterSearch)

        with(holder) {
            binding.tvTitleMovieSearch.text = movieCurrent.title

            binding.ivMoviePosterSearch.setOnClickListener {
                onClickListener(movieCurrent)
            }
        }
    }

}