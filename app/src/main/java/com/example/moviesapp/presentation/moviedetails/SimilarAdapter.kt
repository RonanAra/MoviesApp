package com.example.moviesapp.presentation.moviedetails

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviesapp.data.model.Movie
import com.example.moviesapp.databinding.ItemListHorizontalMoviesBinding

class SimilarAdapter(
    private val listMovies: List<Movie>
) : RecyclerView.Adapter<SimilarAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemListHorizontalMoviesBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listMovies[position])
    }

    override fun getItemCount(): Int = listMovies.size

    class ViewHolder(private val binding: ItemListHorizontalMoviesBinding) :
        RecyclerView.ViewHolder(binding.root) {


        fun bind(movies: Movie) {
            with(binding) {

                Glide
                    .with(itemView.context)
                    .load(movies.poster_path)
                    .into(ivMoviePoster)
            }
        }
    }
}