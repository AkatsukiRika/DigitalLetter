package com.drm.to.ssy.digitalletter.widget

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import com.drm.to.ssy.digitalletter.model.MemoryConfig

@Composable
fun IntroMaskLayout(alpha: Float, config: MemoryConfig) {
    Box(modifier = Modifier
        .fillMaxSize()
        .alpha(alpha)
        .background(config.themeColor)
    ) {
        Image(
            painter = painterResource(config.introDrawableRes),
            contentDescription = null,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}