package com.example.popularmovies.navigation

import kotlinx.serialization.Serializable

sealed class ScreenDestinations {
    @Serializable
    data object SplashScreen : ScreenDestinations()

    @Serializable
    data object MovieListScreen : ScreenDestinations()

    @Serializable
    data class MovieDetailScreen(val id: Long) : ScreenDestinations()
}