package com.drm.to.ssy.digitalletter.widget

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animate
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import com.drm.to.ssy.digitalletter.R
import com.drm.to.ssy.digitalletter.engine.Engine
import com.drm.to.ssy.digitalletter.model.MemoryConfig
import com.drm.to.ssy.digitalletter.ui.theme.FontBold
import com.drm.to.ssy.digitalletter.ui.theme.SerifBold
import com.drm.to.ssy.digitalletter.utils.performAudioFadeOut
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.LocalDate
import java.time.temporal.ChronoUnit

@Composable
fun MemoryMonologueScreen(config: MemoryConfig, onActivityJump: () -> Unit) {
    var maskAlpha by remember { mutableFloatStateOf(1f) }

    Box(modifier = Modifier.fillMaxSize()) {
        MainLayout(maskAlpha, config, onActivityJump)

        IntroMaskLayout(maskAlpha, config)
    }

    LaunchedEffect(Unit) {
        animate(
            initialValue = 1f,
            targetValue = 0f,
            animationSpec = tween(durationMillis = 3000, easing = LinearEasing, delayMillis = 5000),
        ) { value, _ ->
            maskAlpha = value
        }
    }
}

@Composable
private fun MainLayout(
    maskAlpha: Float,
    config: MemoryConfig,
    onActivityJump: () -> Unit
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val engine = remember {
        Engine(config.cmdList)
    }
    val audioPlayer = remember {
        ExoPlayer.Builder(context).build().apply {
            repeatMode = ExoPlayer.REPEAT_MODE_ONE
        }
    }
    val currentMusicRes = engine.currentMusicRes.collectAsState(initial = 0).value
    val currentTitle = engine.currentTitle.collectAsState(initial = "").value
    val currentText = engine.currentText.collectAsState(initial = "").value
    var isInTextAnimation by remember { mutableStateOf(true) }
    val textAlpha = remember { Animatable(0f) }

    DisposableEffect(Unit) {
        onDispose {
            audioPlayer.release()
        }
    }

    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.White)
        .clickable(
            enabled = maskAlpha == 0f && !isInTextAnimation,
            interactionSource = remember { MutableInteractionSource() },
            indication = null
        ) {
            scope.launch {
                isInTextAnimation = true
                textAlpha.snapTo(1f)
                textAlpha.animateTo(
                    targetValue = 0f,
                    animationSpec = tween(durationMillis = 1000, easing = LinearEasing)
                )
                isInTextAnimation = false
                if (!engine.goNext()) {
                    performAudioFadeOut(audioPlayer) {
                        onActivityJump()
                    }
                }
            }
        }
    ) {
        val text = if (currentText == context.getString(R.string.ver_099_02)) {
            val targetDate = LocalDate.of(2025, 5, 4)
            val currentDate = LocalDate.now()
            val daysBetween = ChronoUnit.DAYS.between(targetDate, currentDate)
            currentText.format(daysBetween)
        } else {
            currentText
        }

        Text(
            text = text,
            modifier = Modifier
                .align(Alignment.Center)
                .alpha(textAlpha.value),
            style = SerifBold.copy(color = Color.Black, fontSize = 24.sp)
        )

        Box(modifier = Modifier
            .align(Alignment.TopCenter)
            .padding(top = 16.dp)
            .width(128.dp)
            .height(44.dp)
            .background(config.themeColor.copy(alpha = 0.8f), shape = RoundedCornerShape(10.dp))
        ) {
            Text(
                text = currentTitle,
                style = FontBold.copy(color = Color.LightGray, fontSize = 16.sp),
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }

    LaunchedEffect(maskAlpha) {
        if (maskAlpha == 0f) {
            engine.goNext()
        }
    }

    LaunchedEffect(currentMusicRes) {
        if (currentMusicRes != 0) {
            val audioUri = "android.resource://${context.packageName}/${currentMusicRes}".toUri()
            audioPlayer.setMediaItem(MediaItem.fromUri(audioUri))
            audioPlayer.prepare()
            audioPlayer.play()
        } else {
            audioPlayer.stop()
        }
    }

    LaunchedEffect(currentText) {
        if (currentText.isNotEmpty()) {
            isInTextAnimation = true
            textAlpha.snapTo(0f)
            textAlpha.animateTo(
                targetValue = 1f,
                animationSpec = tween(durationMillis = 1000, easing = LinearEasing)
            )
            isInTextAnimation = false
        } else {
            isInTextAnimation = false
        }
    }
}