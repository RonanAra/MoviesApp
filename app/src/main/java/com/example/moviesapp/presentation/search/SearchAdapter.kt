package com.example.moviesapp.presentation.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesapp.data.model.Movie
import com.example.moviesapp.databinding.ItemSearchMoviesBinding

class SearchAdapter(

) : ListAdapter<Movie, SearchAdapter.ViewHolder>(Movie.DIFF_CALLBACK) {

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

    }

    inner class ViewHolder(private val binding: ItemSearchMoviesBinding) :
        RecyclerView.ViewHolder(binding.root)

}