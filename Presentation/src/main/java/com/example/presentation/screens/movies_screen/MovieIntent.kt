package com.example.presentation.screens.movies_screen

sealed class MovieIntent {
    class GetMovies(val forceReload: Boolean) : MovieIntent()
    class OpenMovieDetails(val id: String) : MovieIntent()
    class OnSearchChanged(val search: String) : MovieIntent()
    class UpdateIsRefreshing(val isRefreshing: Boolean) : MovieIntent()
    object ClearSearch : MovieIntent()
}