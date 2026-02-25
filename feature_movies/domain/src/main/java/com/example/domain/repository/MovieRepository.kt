package com.example.domain.repository

import com.example.domain.models.MovieModel

interface MovieRepository {
    suspend fun getMovie(page: Int): MovieModel
}