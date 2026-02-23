package com.example.presentation.screens.movies_screen

import com.example.domain.models.MovieItem

data class MovieState (
    val isLoading: Boolean = false,
    val movies: List<MovieItem> = emptyList(),
    val loadFinished: Boolean = false,
    val isRefreshing: Boolean = false,
    val error: String = "",
    val search: String = ""
)