package com.example.moviesapp.presentation.home.adapter

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.example.moviesapp.domain.entity.MoviesModel

class HomeMoviesAdapter(
    private val onClickItemListener: (MoviesModel) -> Unit
) : PagingDataAdapter<MoviesModel, MoviesListVIewHolder>(diffCallback) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MoviesListVIewHolder {
        return MoviesListVIewHolder.create(
            parent = parent,
            onItemClickListener = onClickItemListener
        )
    }

    override fun onBindViewHolder(
        holder: MoviesListVIewHolder,
        position: Int
    ) {
        getItem(position)?.let { holder.bind(it) }
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