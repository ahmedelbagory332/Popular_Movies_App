package com.example.data.repositoryImpl

import com.example.core.network.api.ApiServices
import com.example.core.network.utils.safeApiCall
import com.example.data.mapper.toModel
import com.example.domain.models.MovieModel
import com.example.domain.repository.MovieRepository
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val api: ApiServices
) : MovieRepository {

    override suspend fun getMovie(page: Int): MovieModel {
        return safeApiCall { api.getMovies(page = page).toModel() }
    }
}