package com.example.domain.models

data class MovieModel(
    val moveList: List<MovieItem> = emptyList(),
    val page: Long = 0L,
    val totalPages: Long = 0L,
    val totalResults: Long = 0L,
    )

data class MovieItem(
    val id: Long = 0L,
    val posterUrl: String = "",
    val backdropUrl: String = "",
    val name: String = "",
    val date: String = "",
    val rate: Double = 0.0,
)

data class GenreModel(
    val genres: List<GenreItemModel> = emptyList(),
)

data class GenreItemModel(
    val id: Long = -1,
    val name: String = "",
)