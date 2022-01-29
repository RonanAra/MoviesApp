package com.example.moviesapp.presentation.search.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviesapp.data.model.Movie
import com.example.moviesapp.databinding.ItemSearchMoviesBinding

class SearchAdapter(
    private val onClickListener: (movie: Movie) -> Unit)
    : RecyclerView.Adapter<SearchAdapter.SerieViewHolder>() {

    inner class SerieViewHolder(val binding: ItemSearchMoviesBinding) :
        RecyclerView.ViewHolder(binding.root)

    private val diffCallBack = object : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return newItem == oldItem
        }

    }

    private val differ = AsyncListDiffer(this, diffCallBack)
    var movies: List<Movie>
        get() = differ.currentList
        set(value) {
            differ.submitList(value)
        }

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
        val movieAtual = movies[position]
        Glide
            .with(holder.binding.ivMoviePosterSearch)
            .load(movieAtual.poster_path)
            .into(holder.binding.ivMoviePosterSearch)

        with(holder){
            binding.tvTitleMovieSearch.text = movieAtual.title
            binding.ivMoviePosterSearch.setOnClickListener {
                onClickListener(movieAtual)
            }
        }
    }

    override fun getItemCount() = movies.size

}