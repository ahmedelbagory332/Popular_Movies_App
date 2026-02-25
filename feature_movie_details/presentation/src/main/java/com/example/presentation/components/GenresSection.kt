package com.example.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.design_system.colors.Purple80
import com.example.design_system.colors.PurpleGrey40
import com.example.domain.models.GenreItemModel

@Composable
fun GenresSection(modifier: Modifier, genres: List<GenreItemModel>) {
    Text(
        text = "Genres",
        style = TextStyle(
            color = Color.White,
            fontSize = 24.sp,
        ),
        modifier = modifier

    )
    Spacer(modifier = Modifier.height(6.dp))
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .horizontalScroll(rememberScrollState()),
    ) {
        genres.forEach { genre ->
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
}