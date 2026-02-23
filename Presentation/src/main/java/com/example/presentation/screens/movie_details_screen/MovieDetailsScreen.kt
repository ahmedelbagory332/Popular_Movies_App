package com.example.presentation.screens.movie_details_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.presentation.common.ChangeSystemUiColors
import com.example.presentation.common.ErrorHolder
import com.example.presentation.screens.movie_details_screen.components.MovieDetailsContent
import com.example.presentation.screens.movies_screen.components.LoadingMoviesIndicator
import com.example.presentation.ui.theme.PurpleGrey40

@Composable
fun MovieDetailsScreen(
    viewModel: MovieDetailsViewModel = hiltViewModel(),
    onBackClick: () -> Unit,
) {


    val state by viewModel.state.collectAsState()
    val isLoading by remember { derivedStateOf { state.isLoading } }
    val movieDetails by remember { derivedStateOf { state.movieDetails } }
    val error by remember { derivedStateOf { state.error } }
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
        if (isLoading)
            LoadingMoviesIndicator(ctx)


        if (error.isNotEmpty())
            ErrorHolder(text = error) {
                viewModel.sendIntent(MovieDetailsIntent.GetMovieDetails)
            }

        if (!isLoading && error.isEmpty())
            MovieDetailsContent(
                movie = movieDetails,
                onBackClick = onBackClick,
            )

    }
}





