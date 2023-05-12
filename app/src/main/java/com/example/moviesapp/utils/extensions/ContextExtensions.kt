package com.example.moviesapp.utils.extensions

import android.content.Context
import android.widget.Toast

fun Context.createToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}