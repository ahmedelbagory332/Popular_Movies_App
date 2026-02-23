package com.example.data.mapper

import com.example.data.models.GenreItemNetWorkModel
import com.example.data.models.GenreNetWorkModel
import com.example.data.models.MovieDetailsNetWorkModel
import com.example.data.models.MovieItemNetworkModel
import com.example.data.models.MovieNetworkModel
import com.example.data.models.ProductionCompanyDto
import com.example.domain.models.GenreItemModel
import com.example.domain.models.GenreModel
import com.example.domain.models.MovieDetailsModel
import com.example.domain.models.MovieItem
import com.example.domain.models.MovieModel
import com.example.domain.models.ProductionCompany

fun GenreNetWorkModel.toModel(): GenreModel =
    GenreModel(
        genres = genres.map { it.toModel() }
    )

fun GenreItemNetWorkModel.toModel(): GenreItemModel =
    GenreItemModel(
        id = id,
        name = name
    )

fun MovieItemNetworkModel.toModel(): MovieItem =
    MovieItem(
        id = id,
        posterUrl = posterPath,
        backdropUrl = backdropPath,
        name = title,
    )

fun MovieNetworkModel.toModel(): MovieModel =
    MovieModel(
        moveList = movieList.map { it.toModel() },
        page = page,
        totalPages = totalPages,
        totalResults = totalResults
    )

fun MovieDetailsNetWorkModel.toModel(): MovieDetailsModel =
    MovieDetailsModel(
        id = id,
        posterUrl = posterPath,
        name = title,
        tagline = tagline,
        releaseDate = releaseDate,
        runtime = runtime,
        overview = overview,
        genres = genres.map { it.toModel() },
        productionCompanies = productionCompanies.map { it.toModel() },
    )

fun ProductionCompanyDto.toModel(): ProductionCompany =
    ProductionCompany(
        id = id,
        logoPath = logoPath,
        name = name,
        originCountry = originCountry
    )
