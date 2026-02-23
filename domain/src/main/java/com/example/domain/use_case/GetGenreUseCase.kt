package com.example.domain.use_case

import com.example.domain.BaseUseCase
import com.example.domain.models.GenreModel
import com.example.domain.repository.MovieRepository

class GetGenreUseCase(
    private val repository: MovieRepository
) : BaseUseCase<GenreModel, Unit>() {

    override suspend fun execute(params: Unit): GenreModel {
        return repository.getGenre()
    }
}