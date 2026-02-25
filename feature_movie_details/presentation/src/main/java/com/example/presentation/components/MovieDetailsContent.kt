package com.example.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.design_system.R
import com.example.design_system.colors.PurpleGrey40
import com.example.domain.models.MovieDetailsModel

@Composable
fun MovieDetailsContent(
    movie: MovieDetailsModel,
    onBackClick: () -> Unit,
) {
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp)
        ) {
            Image(
                painter = painterResource(R.drawable.outline_arrow_back_24),
                contentDescription = null,
                modifier = Modifier
                    .padding(start = 16.dp, top = 30.dp)
                    .clip(RoundedCornerShape(32.dp))
                    .background(Color.White.copy(alpha = .2f))
                    .padding(8.dp)
                    .clickable { onBackClick() }
            )


            AsyncImage(
                model = ImageRequest.Builder(context).data(movie.backdropPath)
                    .build(),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop,
                alpha = 0.2f
            )

            AsyncImage(
                model = ImageRequest.Builder(context).data(movie.posterUrl)
                    .build(),
                contentDescription = null,
                modifier = Modifier
                    .size(200.dp, 300.dp)
                    .clip(RoundedCornerShape(26.dp))
                    .align(Alignment.BottomCenter),
                contentScale = ContentScale.Crop,
            )

            Box(
                modifier = Modifier
                    .height(100.dp)
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .background(
                        brush = Brush.linearGradient(
                            colors = listOf(
                                PurpleGrey40.copy(alpha = .2f),
                                PurpleGrey40,
                            ),
                            start = Offset(0f, 0f),
                            end = Offset(0f, Float.POSITIVE_INFINITY)
                        )
                    )
            )

        }

        MovieDetailsBody(movie = movie)
    }
}