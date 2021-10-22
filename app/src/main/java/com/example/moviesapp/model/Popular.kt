package com.example.moviesapp.model

import android.os.Parcelable
import com.example.moviesapp.model.Result
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Popular(
    val page: Int,
    val results: List<Result>,
    val total_pages: Int,
    val total_results: Int
) : Parcelable