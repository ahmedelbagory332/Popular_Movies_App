package com.example.domain.use_case

import com.example.domain.BaseUseCase
import com.example.domain.models.MovieDetailsModel
import com.example.domain.repository.MovieRepository
import javax.inject.Inject

class GetMovieDetailsUseCase @Inject constructor(
    private val repository: MovieRepository
) : BaseUseCase<MovieDetailsModel, Long>() {

    override suspend fun execute(params: Long): MovieDetailsModel {
        return repository.getMovieDetails(movieId = params.toString())
    }
}