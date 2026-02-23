package com.example.presentation.screens.splash_screen.components

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.decode.GifDecoder
import coil.request.ImageRequest
import com.example.presentation.R
import com.example.presentation.ui.theme.PurpleGrey40

@Composable
fun SplashScreenBody(
    ctx: Context,
    progress: Float
) {
    val animationDuration = 4000
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = PurpleGrey40)
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                model = ImageRequest.Builder(ctx)
                    .data(R.drawable.logo)
                    .decoderFactory(GifDecoder.Factory())
                    .build(),
                contentDescription = "Logo",
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(26.dp))

            AnimatedProgressBar(progress = progress, animationDuration = animationDuration)
        }

    }
}