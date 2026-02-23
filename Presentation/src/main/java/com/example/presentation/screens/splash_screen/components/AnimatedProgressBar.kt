package com.example.presentation.screens.splash_screen.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.presentation.ui.theme.Pink80
import com.example.presentation.ui.theme.PurpleGrey80


@Composable
fun AnimatedProgressBar(progress: Float, animationDuration: Int) {
    val animatedProgress by animateFloatAsState(
        targetValue = progress,
        animationSpec = tween(durationMillis = animationDuration)
    )

    LinearProgressIndicator(
        progress = { animatedProgress },
        color = PurpleGrey80,
        trackColor = Pink80,
        modifier = Modifier
            .height(16.dp)
            .padding(horizontal = 4.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(50))
    )
}