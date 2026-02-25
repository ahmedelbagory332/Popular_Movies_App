package com.example.data.mapper

import com.example.core.BuildConfig
import com.example.core.network.models.GenreItemNetWorkModel
import com.example.core.network.models.GenreNetWorkModel
import com.example.core.network.models.MovieItemNetworkModel
import com.example.core.network.models.MovieNetworkModel
import com.example.domain.models.GenreItemModel
import com.example.domain.models.GenreModel
import com.example.domain.models.MovieItem
import com.example.domain.models.MovieModel

fun GenreNetWorkModel.toModel(): GenreModel =
    GenreModel(
        genres = genres?.map { it.toModel() } ?: emptyList()
    )

fun GenreItemNetWorkModel.toModel(): GenreItemModel =
    GenreItemModel(
        id = id,
        name = name ?: ""
    )

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
