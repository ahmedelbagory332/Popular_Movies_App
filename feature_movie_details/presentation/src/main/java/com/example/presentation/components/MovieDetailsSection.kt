package com.example.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MovieDetailsSection(
    modifier: Modifier,
    sectionTitle: String,
    sectionSubTitle: String,
    sectionTitleStyle: TextStyle = TextStyle(
        color = Color.White,
        fontSize = 16.sp,
    ),
    sectionSubTitleStyle: TextStyle = TextStyle(
        color = Color.White,
        fontSize = 14.sp,
    ),
    sectionHorizontalAlignment: Alignment.Horizontal = Alignment.CenterHorizontally
) {
    Column(
        modifier = modifier,
        horizontalAlignment = sectionHorizontalAlignment
    ) {
        Text(
            text = sectionTitle,
            style = sectionTitleStyle
        )
        Spacer(modifier = Modifier.height(3.dp))
        Text(
            text = sectionSubTitle,
            style = sectionSubTitleStyle
        )
    }
}