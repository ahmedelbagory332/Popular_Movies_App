package com.example.presentation

import com.example.core_domain.base.ViewIntent

sealed class MovieIntent() : ViewIntent {
    class GetMovies(val forceReload: Boolean) : MovieIntent()
    class OpenMovieDetails(val id: String) : MovieIntent()
    class OnSearchChanged(val search: String) : MovieIntent()
    class UpdateIsRefreshing(val isRefreshing: Boolean) : MovieIntent()
    object ClearSearch : MovieIntent()
    object OnRefreshing : MovieIntent()
}