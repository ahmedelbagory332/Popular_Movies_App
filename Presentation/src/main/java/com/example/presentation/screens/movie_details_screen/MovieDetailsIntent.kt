package com.example.presentation.screens.movie_details_screen

sealed class MovieDetailsIntent {
    object GetMovieDetails : MovieDetailsIntent()
}