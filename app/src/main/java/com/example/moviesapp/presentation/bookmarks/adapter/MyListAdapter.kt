package com.example.moviesapp.presentation.bookmarks.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviesapp.presentation.interfaces.MovieOnClickListener
import com.example.moviesapp.R
import com.example.moviesapp.data.model.Movie
import com.example.moviesapp.databinding.ItemListHorizontalMoviesBinding

class MyListAdapter(
    private val listMovieFavorites: ArrayList<Movie>
) : RecyclerView.Adapter<MyListAdapter.MyViewHolder>() {

    private lateinit var movieOnClickListener: MovieOnClickListener

    fun setOnClickListener(movieOnClickListener: MovieOnClickListener) {
        this.movieOnClickListener = movieOnClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemListHorizontalMoviesBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MyViewHolder(binding, movieOnClickListener)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(listMovieFavorites[position], movieOnClickListener)

    }

    override fun getItemCount(): Int {
        return listMovieFavorites.size
    }


    class MyViewHolder(
        val binding: ItemListHorizontalMoviesBinding,
        movieOnClickListener: MovieOnClickListener
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(
            moviesFavorites: Movie?,
            movieOnClickListener: MovieOnClickListener
        ) {

            with(binding) {

                itemView.setOnClickListener {
                    if (moviesFavorites != null) {
                        movieOnClickListener.onItemClick(moviesFavorites)
                    }
                }

                Glide
                    .with(itemView.context)
                    .load("https://image.tmdb.org/t/p/w500/${moviesFavorites?.poster_path}")
                    .into(ivMoviePoster)
            }

        }
    }
}




