package com.example.domain

import com.example.domain.models.MovieDetailsModel

object MovieDetailsTestFactory {

    fun movieDetails() = MovieDetailsModel(
        id = 1,
        name = "Movie Details",
        overview = "Test Overview",
        posterUrl = "poster.jpg"
    )
}