package com.example.presentation.screens.movies_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.domain.models.MovieItem
import com.example.presentation.common.ChangeSystemUiColors
import com.example.presentation.common.ErrorHolder
import com.example.presentation.common.SearchBar
import com.example.presentation.screens.movies_screen.components.LoadingMoviesIndicator
import com.example.presentation.screens.movies_screen.components.MovieList
import com.example.presentation.ui.theme.PurpleGrey40

@Composable
fun MovieScreen(
    viewModel: MovieViewModel = hiltViewModel(),
    onMovieClick: (MovieItem) -> Unit
) {
    val ctx = LocalContext.current
    val state by viewModel.state.collectAsState()
    val isLoading by remember { derivedStateOf { state.isLoading } }
    val error by remember { derivedStateOf { state.error } }
    val isRefreshing by remember { derivedStateOf { state.isRefreshing } }
    val loadFinished by remember { derivedStateOf { state.loadFinished } }
    val search by remember { derivedStateOf { state.search } }
    val gridState = rememberLazyGridState()

    ChangeSystemUiColors()
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = PurpleGrey40)
            .statusBarsPadding()
            .navigationBarsPadding()
    ) {
        Column {
            Spacer(modifier = Modifier.height(10.dp))
            if (isLoading)
                LoadingMoviesIndicator(ctx)


            if (error.isNotEmpty())
                ErrorHolder(text = error) {
                    viewModel.sendIntent(MovieIntent.GetMovies(forceReload = true))
                }

            if (!isLoading && error.isEmpty()) {
                SearchBar(
                    searchValue = search,
                    onValueChanged = {
                        viewModel.sendIntent(MovieIntent.OnSearchChanged(it))
                    },
                    onClearClick = {
                        viewModel.sendIntent(MovieIntent.ClearSearch)
                    }
                )
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = "Popular Movies \uD83D\uDD25",
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                    ),
                    modifier = Modifier.padding(start = 16.dp)
                )
                Spacer(modifier = Modifier.height(20.dp))
                MovieList(
                    isRefreshing = isRefreshing,
                    movies = state.movies,
                    viewModel = viewModel,
                    gridState = gridState,
                    onMovieClick = onMovieClick,
                    loadFinished = loadFinished
                )
                LaunchedEffect(gridState) {
                    snapshotFlow { gridState.layoutInfo.visibleItemsInfo.lastOrNull()?.index }
                        .collect { lastVisible ->
                            if (lastVisible != null && lastVisible >= state.movies.size - 1 && !loadFinished && search.isEmpty()) {
                                viewModel.sendIntent(MovieIntent.GetMovies(forceReload = false))
                            }
                        }
                }
            }
        }

    }
}