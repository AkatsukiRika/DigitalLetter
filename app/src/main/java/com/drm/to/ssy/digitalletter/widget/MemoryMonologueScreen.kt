package com.drm.to.ssy.digitalletter.widget

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animate
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.drm.to.ssy.digitalletter.model.MemoryConfig

@Composable
fun MemoryMonologueScreen(config: MemoryConfig, onActivityJump: () -> Unit) {
    var maskAlpha by remember { mutableFloatStateOf(1f) }

    Box(modifier = Modifier.fillMaxSize()) {
        IntroMaskLayout(maskAlpha, config)
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