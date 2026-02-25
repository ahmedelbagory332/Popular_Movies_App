package com.example.data.mapper

import com.example.core.BuildConfig
import com.example.core.network.models.GenreItemNetWorkModel
import com.example.core.network.models.MovieDetailsNetWorkModel
import com.example.domain.models.GenreItemModel
import com.example.domain.models.MovieDetailsModel


fun MovieDetailsNetWorkModel.toModel(): MovieDetailsModel =
    MovieDetailsModel(
        id = id,
        posterUrl = BuildConfig.IMAGE_BASE_URL + posterPath,
        backdropPath = BuildConfig.IMAGE_BASE_URL + backdropPath,
        name = title,
        voteCount = voteCount,
        voteAverage = voteAverage,
        tagline = tagline,
        releaseDate = releaseDate,
        runtime = runtime,
        overview = overview,
        genres = genres.map { it.toModel() },
    )

fun GenreItemNetWorkModel.toModel(): GenreItemModel =
    GenreItemModel(
        id = id,
        name = name ?: ""
    )
