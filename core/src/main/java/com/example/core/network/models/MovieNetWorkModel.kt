package com.example.core.network.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MovieNetworkModel(
    val page: Long,
    @field:Json(name = "results")
    val movieList: List<MovieItemNetworkModel>?,
    @field:Json(name = "total_pages")
    val totalPages: Long,
    @field:Json(name = "total_results")
    val totalResults: Long,
)

@JsonClass(generateAdapter = true)
data class MovieItemNetworkModel(
    val adult: Boolean?,
    @field:Json(name = "backdrop_path")
    val backdropPath: String?,
    @field:Json(name = "genre_ids")
    val genreIds: List<Long>?,
    val id: Long,
    @field:Json(name = "original_language")
    val originalLanguage: String?,
    @field:Json(name = "original_title")
    val originalTitle: String?,
    val overview: String?,
    val popularity: Double?,
    @field:Json(name = "poster_path")
    val posterPath: String?,
    @field:Json(name = "release_date")
    val releaseDate: String?,
    val title: String?,
    val video: Boolean?,
    @field:Json(name = "vote_average")
    val voteAverage: Double?,
    @field:Json(name = "vote_count")
    val voteCount: Long?,
)