package com.example.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.design_system.colors.PurpleGrey40
import com.example.design_system.components.ChangeSystemUiColors
import com.example.design_system.components.ErrorHolder
import com.example.design_system.components.LoadingMoviesIndicator
import com.example.presentation.components.MovieDetailsContent

@Composable
fun MovieDetailsScreen(
    viewModel: MovieDetailsViewModel = hiltViewModel(),
    onBackClick: () -> Unit,
) {

    val uiState by viewModel.state.collectAsState()
    val ctx = LocalContext.current

    ChangeSystemUiColors()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = PurpleGrey40)
            .statusBarsPadding()
            .navigationBarsPadding()
    ) {
        Spacer(modifier = Modifier.height(10.dp))
        when {
            uiState.isLoading -> {
                LoadingMoviesIndicator(ctx)
            }

            uiState.error.isNotEmpty() -> {
                ErrorHolder(text = uiState.error) {
                    viewModel.sendIntent(MovieDetailsIntent.GetMovieDetails)
                }
            }

            else -> MovieDetailsContent(
                movie = uiState.movieDetails,
                onBackClick = onBackClick,
            )
        }

    }
}