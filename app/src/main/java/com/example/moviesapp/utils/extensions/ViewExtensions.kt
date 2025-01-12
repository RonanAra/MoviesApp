package com.example.moviesapp.utils.extensions

import android.content.Context
import android.widget.Toast
import com.example.moviesapp.R

fun Context.notImplFeature(msg: String? = null) {
    Toast.makeText(
        this,
        msg ?: getString(R.string.not_impl_feature_message),
        Toast.LENGTH_SHORT
    ).show()
}