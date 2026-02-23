package com.example.presentation.screens.movies_screen.components

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.decode.GifDecoder
import coil.request.ImageRequest
import com.example.presentation.R

@Composable
fun LoadingMoviesIndicator(ctx: Context) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
    ) {
        AsyncImage(
            model = ImageRequest.Builder(ctx)
                .data(R.drawable.logo)
                .decoderFactory(GifDecoder.Factory())
                .build(),
            contentDescription = "Logo",
        )
        Spacer(modifier = Modifier.width(width = 8.dp))
        Text(text = "Loading...", color = Color.White)
    }
}