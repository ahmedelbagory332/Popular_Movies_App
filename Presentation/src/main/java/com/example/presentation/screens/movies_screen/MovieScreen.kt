package com.example.presentation.screens.movies_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.presentation.common.ChangeSystemUiColors
import com.example.presentation.ui.theme.PurpleGrey40

@Composable
fun MovieScreen() {
    ChangeSystemUiColors()
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = PurpleGrey40)
    ) {}
}
