package com.example.presentation

import com.example.core_domain.base.ViewState
import com.example.domain.models.MovieItem

data class MovieState(
    val isLoading: Boolean = false,
    val movies: List<MovieItem> = emptyList(),
    val allMovies: List<MovieItem> = emptyList(),
    val loadFinished: Boolean = false,
    val isRefreshing: Boolean = false,
    val error: String = "",
    val search: String = ""
) : ViewState