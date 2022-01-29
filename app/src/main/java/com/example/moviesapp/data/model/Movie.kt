package com.example.moviesapp.data.model

import android.os.Parcelable
import androidx.recyclerview.widget.DiffUtil
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(
    val adult: Boolean? = null,
    var backdrop_path: String? = null,
    val genre_ids: List<Int>? = null,
    val id: Int? = null,
    val original_language: String? = null,
    val original_title: String? = null,
    val overview: String? = null,
    val popularity: Double? = null,
    var poster_path: String? = null,
    val release_date: String? = null,
    val title: String? = null,
    val video: Boolean? = null,
    val vote_count: Int? = null
) : Parcelable {

    companion object {
        var DIFF_CALLBACK: DiffUtil.ItemCallback<Movie> =
            object : DiffUtil.ItemCallback<Movie>() {
                override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                    return oldItem.id == newItem.id
                }

                override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                    return oldItem.id == newItem.id
                }
            }
    }
}