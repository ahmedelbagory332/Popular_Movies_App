package com.example.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.domain.models.MovieDetailsModel


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
            MovieDetailsSection(
                modifier = Modifier.weight(1f),
                sectionTitle = "Release Date",
                sectionSubTitle = movie.releaseDate ?: "",
            )

            MovieDetailsSection(
                modifier = Modifier.weight(1f),
                sectionTitle = "Vote Count",
                sectionSubTitle = movie.voteCount?.toString() ?: "",
            )

            RatingSection(
                modifier = Modifier.weight(1f),
                rate = movie.voteAverage?.toString() ?: ""
            )
        }

        Spacer(modifier = Modifier.height(32.dp))
        GenresSection(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .align(Alignment.Start),
            genres = movie.genres ?: emptyList()
        )

        Spacer(modifier = Modifier.height(16.dp))
        MovieDetailsSection(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .align(Alignment.Start),
            sectionHorizontalAlignment = Alignment.Start,
            sectionTitle = "Run time",
            sectionTitleStyle = TextStyle(
                color = Color.White,
                fontSize = 24.sp,
            ),
            sectionSubTitle = movie.runtime.toString() + " min",
            sectionSubTitleStyle = TextStyle(
                color = Color.White,
                fontSize = 16.sp,
            )
        )

        Spacer(modifier = Modifier.height(16.dp))
        MovieDetailsSection(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .align(Alignment.Start),
            sectionHorizontalAlignment = Alignment.Start,
            sectionTitle = "Overview",
            sectionTitleStyle = TextStyle(
                color = Color.White,
                fontSize = 24.sp,
            ),
            sectionSubTitle = movie.overview ?: "",
            sectionSubTitleStyle = TextStyle(
                color = Color.White,
                fontSize = 16.sp,
            )
        )
        Spacer(modifier = Modifier.height(32.dp))

    }
}

