package com.drm.to.ssy.digitalletter.ui.create

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animate
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.drm.to.ssy.digitalletter.R
import com.drm.to.ssy.digitalletter.ui.theme.ColorYellow

@Composable
fun MemoryOnCreateScreen() {
    var maskAlpha by remember { mutableFloatStateOf(1f) }

    Box(modifier = Modifier.fillMaxSize()) {
        IntroMaskLayout(maskAlpha)
    }

    LaunchedEffect(Unit) {
        animate(
            initialValue = 1f,
            targetValue = 0f,
            animationSpec = tween(durationMillis = 2000, easing = LinearEasing, delayMillis = 1000),
        ) { value, _ ->
            maskAlpha = value
        }
    }
}

@Composable
private fun IntroMaskLayout(alpha: Float) {
    Box(modifier = Modifier
        .fillMaxSize()
        .alpha(alpha)
        .background(ColorYellow)
    ) {
        Image(
            painter = painterResource(R.drawable.img_on_create_intro),
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.Center)
                .width(767.dp)
                .height(488.dp)
        )
    }
}