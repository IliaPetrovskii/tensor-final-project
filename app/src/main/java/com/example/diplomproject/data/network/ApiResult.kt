package com.example.diplomproject.data.network

import com.example.diplomproject.data.database.Movie

data class ApiResult(
    val total: Int,
    val totalPages: Int,
    val items: List<Movie>)
