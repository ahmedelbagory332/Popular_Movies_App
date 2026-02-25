package com.example.presentation

import com.example.core_domain.base.ViewIntent

sealed class MovieDetailsIntent(): ViewIntent {
    object GetMovieDetails : MovieDetailsIntent()
}