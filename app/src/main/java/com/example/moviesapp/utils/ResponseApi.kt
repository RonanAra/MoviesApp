package com.example.moviesapp.utils

sealed class ResponseApi {
    class Success(var data: Any?) : ResponseApi()
    class Error(val message: String) : ResponseApi()
}