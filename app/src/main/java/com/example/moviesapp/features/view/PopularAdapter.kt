package com.example.moviesapp.features.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviesapp.databinding.MoviesCardBinding
import com.example.moviesapp.model.Result


class PopularAdapter(
    private val listaMovies: List<Result>

) :
    RecyclerView.Adapter<PopularAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = MoviesCardBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listaMovies[position])
    }

    override fun getItemCount(): Int = listaMovies.size

    class ViewHolder(val binding: MoviesCardBinding) :
        RecyclerView.ViewHolder(binding.root) {


        fun bind(movies: Result) {
            with(binding) {

                tvitle.text = movies.title

                Glide
                    .with(itemView.context)
                    .load(movies.poster_path)
                    .into(ivImage)

                }
            }
        }
    }








