package com.example.core.network.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MovieDetailsNetWorkModel(
    val adult: Boolean?,
    @field:Json(name = "backdrop_path")
    val backdropPath: String?,
    @field:Json(name = "belongs_to_collection")
    val belongsToCollection: BelongsToCollection?,
    val budget: Long?,
    val genres: List<GenreItemNetWorkModel>,
    val homepage: String?,
    val id: Long,
    @field:Json(name = "imdb_id")
    val imdbId: String?,
    @field:Json(name = "original_language")
    val originalLanguage: String?,
    @field:Json(name = "original_title")
    val originalTitle: String?,
    val overview: String?,
    val popularity: Double?,
    @field:Json(name = "poster_path")
    val posterPath: String?,
    @field:Json(name = "production_companies")
    val productionCompanies: List<ProductionCompanyDto>?,
    @field:Json(name = "production_countries")
    val productionCountries: List<ProductionCountry>?,
    @field:Json(name = "release_date")
    val releaseDate: String?,
    val revenue: Long?,
    val runtime: Long?,
    @field:Json(name = "spoken_languages")
    val spokenLanguages: List<SpokenLanguage>?,
    val status: String?,
    val tagline: String?,
    val title: String?,
    val video: Boolean?,
    @field:Json(name = "vote_average")
    val voteAverage: Double?,
    @field:Json(name = "vote_count")
    val voteCount: Long?,
)

@JsonClass(generateAdapter = true)
data class BelongsToCollection(
    val id: Long,
    val name: String?,
    @field:Json(name = "poster_path")
    val posterPath: String?,
    @field:Json(name = "backdrop_path")
    val backdropPath: String?,
)

@JsonClass(generateAdapter = true)
data class ProductionCompanyDto(
    val id: Long,
    @field:Json(name = "logo_path")
    val logoPath: String?? = null,
    val name: String?,
    @field:Json(name = "origin_country")
    val originCountry: String?,
)

@JsonClass(generateAdapter = true)
data class ProductionCountry(
    @field:Json(name = "iso_3166_1")
    val iso31661: String?,
    val name: String?,
)

@JsonClass(generateAdapter = true)
data class SpokenLanguage(
    @field:Json(name = "english_name")
    val englishName: String?,
    @field:Json(name = "iso_639_1")
    val iso6391: String?,
    val name: String?,
)

@JsonClass(generateAdapter = true)
data class GenreNetWorkModel(
    val genres: ArrayList<GenreItemNetWorkModel>?,
)

@JsonClass(generateAdapter = true)
data class GenreItemNetWorkModel(
    val id: Long,
    val name: String?,
)