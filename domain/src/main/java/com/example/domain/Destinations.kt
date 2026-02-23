package com.example.domain

import kotlinx.serialization.Serializable

sealed class Destinations {
    @Serializable
    data object SplashScreen : Destinations()

    @Serializable
    data object MovieListScreen : Destinations()

    @Serializable
    data class MovieDetailScreen(val id: String) : Destinations()
}