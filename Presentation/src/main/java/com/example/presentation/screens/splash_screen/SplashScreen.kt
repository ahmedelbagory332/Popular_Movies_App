package com.example.presentation.screens.splash_screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import com.example.presentation.common.ChangeSystemUiColors
import com.example.presentation.screens.splash_screen.components.SplashScreenBody
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(openMovieScreen: () -> Unit) {

    var progress by remember { mutableStateOf(0f) }
    val splashDuration = 3000L
    val ctx = LocalContext.current

    LaunchedEffect(Unit) {
        progress = 1f
        delay(splashDuration)
        openMovieScreen()
    }
    ChangeSystemUiColors()
    SplashScreenBody(ctx, progress)
}