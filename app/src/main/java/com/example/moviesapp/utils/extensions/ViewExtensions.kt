package com.example.moviesapp.utils.extensions

import android.content.Context
import android.widget.Toast

fun Context.notImplFeature(msg: String? = null) {
    Toast.makeText(
        this,
        msg ?: "Função não Implementada!",
        Toast.LENGTH_SHORT
    ).show()
}