package com.example.domain.use_case

import com.example.domain.BaseUseCase
import com.example.domain.models.GenreModel
import com.example.domain.models.MovieDetailsModel
import com.example.domain.repository.MovieRepository

class GetMovieDetailsUseCase(
    private val repository: MovieRepository
) : BaseUseCase<MovieDetailsModel, String>() {

    override suspend fun execute(params: String): MovieDetailsModel {
        return repository.getMovieDetails(movieId = params)
    }
}