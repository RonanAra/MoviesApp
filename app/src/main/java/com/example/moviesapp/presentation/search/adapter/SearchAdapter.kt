package com.example.moviesapp.presentation.search.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.alvarengadev.alvaflix.view.interfaces.MovieOnClickListener
import com.bumptech.glide.Glide
import com.example.moviesapp.data.model.Movie
import com.example.moviesapp.databinding.ItemSearchMoviesBinding

class SearchAdapter(

) : ListAdapter<Movie, SearchAdapter.ViewHolder>(Movie.DIFF_CALLBACK) {


    private lateinit var movieOnClickListener: MovieOnClickListener

    fun setMovieOnClickListener(
        movieOnClickListener: MovieOnClickListener
    ) {
        this.movieOnClickListener = movieOnClickListener
    }


    inner class ViewHolder(val binding: ItemSearchMoviesBinding) :
        RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemSearchMoviesBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movieCurrent = getItem(position)
        Glide
            .with(holder.binding.ivMoviePosterSearch)
            .load(movieCurrent.poster_path)
            .into(holder.binding.ivMoviePosterSearch)

        with(holder) {
            binding.tvTitleMovieSearch.text = movieCurrent.title

            binding.ivMoviePosterSearch.setOnClickListener {
                movieOnClickListener.onItemClick(movieCurrent)
            }
        }
    }

}