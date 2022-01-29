package com.example.moviesapp.presentation.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviesapp.databinding.MoviesCardBinding
import com.example.moviesapp.data.model.Movie


class PopularAdapter(
    private val onClickListener: (movies: Movie) -> Unit
) : PagingDataAdapter<Movie, PopularAdapter.ViewHolder>(Movie.DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = MoviesCardBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), onClickListener)

    }


    class ViewHolder(val binding: MoviesCardBinding) :
        RecyclerView.ViewHolder(binding.root) {


        fun bind(movies: Movie?, onClickListener: (movies: Movie) -> Unit) {
            with(binding) {
                movies?.let {
                    tvitle.text = movies?.title
                    mvCard.setOnClickListener {
                        onClickListener(movies)
                    }
                }

                Glide
                    .with(itemView.context)
                    .load(movies?.poster_path)
                    .into(ivImage)


            }
        }
    }
}









