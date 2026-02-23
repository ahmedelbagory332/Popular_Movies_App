package com.example.data.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GenreNetWorkModel(
    val genres: ArrayList<GenreItemNetWorkModel>,
)

@JsonClass(generateAdapter = true)
data class GenreItemNetWorkModel(
    val id: Long,
    val name: String,
)