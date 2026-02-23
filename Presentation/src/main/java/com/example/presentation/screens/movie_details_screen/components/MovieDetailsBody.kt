package com.example.presentation.screens.movie_details_screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.domain.models.MovieDetailsModel
import com.example.presentation.ui.theme.Purple80
import com.example.presentation.ui.theme.PurpleGrey40


@Composable
fun MovieDetailsBody(movie: MovieDetailsModel) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = movie.name ?: "",
            style = TextStyle(
                color = Color.White,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
            ),
            modifier = Modifier
                .padding(top = 10.dp, start = 16.dp, end = 16.dp)
        )

        Spacer(modifier = Modifier.height(26.dp))


        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Release Date",
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 16.sp,
                    )
                )
                Spacer(modifier = Modifier.height(3.dp))
                Text(
                    text = movie.releaseDate ?: "",
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 14.sp,
                    )
                )
            }

            Column(
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Vote Count",
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 16.sp,
                    )
                )
                Spacer(modifier = Modifier.height(3.dp))
                Text(
                    text = movie.voteCount.toString(),
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 14.sp,
                    )
                )
            }

            Column(
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Rating",
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 16.sp,
                    )
                )
                Spacer(modifier = Modifier.height(3.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Icon(
                        imageVector = Icons.Filled.Star,
                        contentDescription = null,
                        tint = Color(0xffffc107),
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = movie.voteAverage.toString(),
                        style = TextStyle(
                            color = Color.White,
                            fontSize = 14.sp,
                        )
                    )
                }
            }
        }


        Spacer(modifier = Modifier.height(32.dp))
        Text(
            text = "Genres",
            style = TextStyle(
                color = Color.White,
                fontSize = 24.sp,
            ),
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .align(Alignment.Start)
        )
        Spacer(modifier = Modifier.height(6.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .horizontalScroll(rememberScrollState()),
        ) {
            movie.genres?.forEach { genre ->
                Text(
                    text = genre.name,
                    style = TextStyle(
                        color = PurpleGrey40,
                        fontSize = 14.sp,
                    ),
                    modifier = Modifier
                        .clip(RoundedCornerShape(26.dp))
                        .background(Purple80)
                        .padding(8.dp)

                )
                Spacer(modifier = Modifier.width(16.dp))
            }

        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Run time",
            style = TextStyle(
                color = Color.White,
                fontSize = 24.sp,
            ),
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .align(Alignment.Start)
        )

        Spacer(modifier = Modifier.height(6.dp))

        Text(
            text = movie.runtime.toString() + " min",
            style = TextStyle(
                color = Color.White,
                fontSize = 16.sp,
            ),
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .align(Alignment.Start)
        )
        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Overview",
            style = TextStyle(
                color = Color.White,
                fontSize = 24.sp,
            ),
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .align(Alignment.Start)
        )

        Spacer(modifier = Modifier.height(6.dp))

        Text(
            text = movie.overview ?: "",
            style = TextStyle(
                color = Color.White,
                fontSize = 16.sp,
            ),
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(32.dp))

    }
}