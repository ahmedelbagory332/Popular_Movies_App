package com.example.presentation.common

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.graphics.Color
import com.example.presentation.ui.theme.PurpleGrey40
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun ChangeSystemUiColors(
    useDarkIcons: Boolean = false,
    statusBarColor: Color = PurpleGrey40,
    navigationBarColor: Color = PurpleGrey40
) {
    val systemUiController = rememberSystemUiController()
    LaunchedEffect(Unit) {
        systemUiController.setStatusBarColor(
            color = statusBarColor,
            darkIcons = useDarkIcons
        )
        systemUiController.setNavigationBarColor(
            color = navigationBarColor,
            darkIcons = useDarkIcons
        )
    }
}