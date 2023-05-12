package com.example.moviesapp.presentation.favorites

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviesapp.data.model.Movie
import com.example.moviesapp.databinding.ItemListHorizontalMoviesBinding

class FavoritesAdapter(
    private val listMovieFavorites: List<Movie>,
) : RecyclerView.Adapter<FavoritesAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): MyViewHolder {
        val binding = ItemListHorizontalMoviesBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: MyViewHolder,
        position: Int
    ) {
        holder.bind(listMovieFavorites[position])
    }

    override fun getItemCount(): Int = listMovieFavorites.size

    class MyViewHolder(
        private val binding: ItemListHorizontalMoviesBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(moviesFavorites: Movie) = binding.run {
            Glide
                .with(itemView.context)
                .load(moviesFavorites.poster_path)
                .into(ivMoviePoster)
        }
    }
}