package com.example.moviesapp.presentation.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviesapp.data.model.Movie
import com.example.moviesapp.databinding.ItemListHorizontalMoviesBinding
import com.example.moviesapp.presentation.interfaces.MovieOnClickListener


class PopularAdapter(
) : PagingDataAdapter<Movie, PopularAdapter.ViewHolder>(Movie.DIFF_CALLBACK) {


    private lateinit var movieOnClickListener: MovieOnClickListener

    fun setMovieOnClickListener(
        movieOnClickListener: MovieOnClickListener
    ) {
        this.movieOnClickListener = movieOnClickListener
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemListHorizontalMoviesBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), movieOnClickListener)

    }


    class ViewHolder(val binding: ItemListHorizontalMoviesBinding) :
        RecyclerView.ViewHolder(binding.root) {


        fun bind(
            movies: Movie?,
            movieOnClickListener: MovieOnClickListener
        ) {
            with(binding) {
                movies?.let {
                    itemList.setOnClickListener {
                        movieOnClickListener.onItemClick(movies)
                    }
                }

                Glide
                    .with(itemView.context)
                    .load(movies?.poster_path)
                    .into(ivMoviePoster)


            }
        }
    }
}









