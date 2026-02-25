package com.example.data.mapper

import com.example.core.BuildConfig
import com.example.core.network.models.MovieItemNetworkModel
import com.example.core.network.models.MovieNetworkModel
import com.example.domain.models.MovieItem
import com.example.domain.models.MovieModel

fun MovieItemNetworkModel.toModel(): MovieItem =
    MovieItem(
        id = id,
        posterUrl =  BuildConfig.IMAGE_BASE_URL + posterPath,
        backdropUrl = BuildConfig.IMAGE_BASE_URL + backdropPath,
        name = title ?: "",
        date = releaseDate ?: "",
        rate = voteAverage ?: 0.0
    )

fun MovieNetworkModel.toModel(): MovieModel =
    MovieModel(
        moveList = movieList?.map { it.toModel() } ?: emptyList(),
        page = page,
        totalPages = totalPages,
        totalResults = totalResults
    )
