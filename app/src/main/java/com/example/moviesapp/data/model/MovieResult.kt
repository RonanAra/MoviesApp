package com.example.moviesapp.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieResult(
    val results: List<Movie>
) : Parcelable