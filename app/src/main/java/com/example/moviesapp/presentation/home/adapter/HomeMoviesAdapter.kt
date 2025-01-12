package com.example.moviesapp.presentation.home.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesapp.domain.entity.MoviesModel

class HomeMoviesAdapter(
    private val onClickItemListener: (MoviesModel) -> Unit
) : RecyclerView.Adapter<MoviesListVIewHolder>() {

    private val asyncListDiffer = AsyncListDiffer(this, diffCallback)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MoviesListVIewHolder {
        return MoviesListVIewHolder.create(
            parent = parent,
            onItemClickListener = onClickItemListener
        )
    }

    fun updateMovies(movies: List<MoviesModel>) {
        asyncListDiffer.submitList(movies)
    }

    override fun getItemCount() = asyncListDiffer.currentList.size

    override fun onBindViewHolder(
        holder: MoviesListVIewHolder,
        position: Int
    ) {
        holder.bind(asyncListDiffer.currentList[position])
    }

    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<MoviesModel>() {
            override fun areItemsTheSame(
                oldItem: MoviesModel,
                newItem: MoviesModel
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: MoviesModel,
                newItem: MoviesModel
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}