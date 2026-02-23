package com.example.presentation.screens.movie_details_screen

import com.example.domain.models.MovieDetailsModel

data class MovieDetailsState (
    val isLoading: Boolean = false,
    val movieDetails: MovieDetailsModel = MovieDetailsModel(),
    val error: String = "",
)