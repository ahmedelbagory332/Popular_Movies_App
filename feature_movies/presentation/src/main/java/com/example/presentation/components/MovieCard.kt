package com.example.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.example.design_system.colors.Black
import com.example.domain.models.MovieItem


@Composable
fun MovieCard(movie: MovieItem, onClick: (MovieItem) -> Unit) {
    Column(
        modifier = Modifier
            .padding(4.dp)
            .width(130.dp)
            .clip(RoundedCornerShape(8.dp))
            .clickable { onClick(movie) }
            .background(
                color = Black
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val context = LocalContext.current


        AsyncImage(
            model = ImageRequest.Builder(context).data(movie.posterUrl)
                .crossfade(true)
                .diskCachePolicy(CachePolicy.ENABLED)
                .memoryCachePolicy(CachePolicy.ENABLED)
                .build(),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
                .background(Color.Gray),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = movie.name,
            modifier = Modifier.padding(start = 4.dp),
            style = TextStyle(
                color = Color.White,
                fontSize = 12.sp,
            ),
            maxLines = 1
        )

        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = movie.date,
            modifier = Modifier.padding(start = 4.dp),
            style = TextStyle(
                color = Color.White,
                fontSize = 12.sp,
            ),
            maxLines = 1
        )

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(start = 4.dp, bottom = 4.dp),
        ) {
            Icon(
                imageVector = Icons.Filled.Star,
                contentDescription = null,
                tint = Color(0xffffc107),
                modifier = Modifier.size(16.dp)
            )

            Spacer(modifier = Modifier.width(4.dp))

            Text(
                text = movie.rate.toString(),
                style = TextStyle(
                    color = Color.White,
                    fontSize = 12.sp,
                ),
                maxLines = 1
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

    }
}