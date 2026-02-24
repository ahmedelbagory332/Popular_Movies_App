package com.example.domain

import com.example.domain.models.MovieDetailsModel
import com.example.domain.models.MovieItem
import com.example.domain.models.MovieModel

object MovieTestFactory {

    fun movieModel() = MovieModel(
        moveList = listOf(
            MovieItem(
                id = 1,
                name = "Movie Title",
                posterUrl = "poster.jpg",
                backdropUrl = "back.jpg",
                rate = 8.5
            )
        )
    )

    fun movieDetails() = MovieDetailsModel(
        id = 1,
        name = "Movie Details",
        overview = "Test Overview",
        posterUrl = "poster.jpg"
    )
}