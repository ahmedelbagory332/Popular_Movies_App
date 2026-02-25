package com.example.domain.models

data class MovieDetailsModel(
    val id: Long = 0L,
    val posterUrl: String? = "",
    val backdropPath: String? = "",
    val name: String? = "",
    val tagline: String? = "",
    val releaseDate: String? = "",
    val voteCount: Long? = 0L,
    val voteAverage: Double? = 0.0,
    val runtime: Long? = 0L,
    val overview: String? = "",
    val genres: List<GenreItemModel>? = emptyList(),
    val productionCompanies: List<ProductionCompany>? = emptyList(),
)

data class ProductionCompany(
    val id: Long = 0L,
    val logoPath: String? = "",
    val name: String = "",
    val originCountry: String = "",
)

data class GenreItemModel(
    val id: Long = -1,
    val name: String = "",
)