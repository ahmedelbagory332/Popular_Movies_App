package com.example.presentation

import com.example.core_domain.base.ViewState
import com.example.domain.models.MovieDetailsModel

data class MovieDetailsState (
    val isLoading: Boolean = false,
    val movieDetails: MovieDetailsModel = MovieDetailsModel(),
    val error: String = "",
): ViewState