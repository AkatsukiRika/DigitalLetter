package com.drm.to.ssy.digitalletter.ui.create

import androidx.annotation.OptIn
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animate
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.net.toUri
import androidx.media3.common.MediaItem
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.AspectRatioFrameLayout
import androidx.media3.ui.PlayerView
import com.drm.to.ssy.digitalletter.R
import com.drm.to.ssy.digitalletter.engine.Engine
import com.drm.to.ssy.digitalletter.engine.getCmdList097
import com.drm.to.ssy.digitalletter.ui.theme.ColorYellow
import com.drm.to.ssy.digitalletter.ui.theme.FontRegular
import kotlinx.coroutines.delay

@Composable
fun MemoryOnCreateScreen() {
    var maskAlpha by remember { mutableFloatStateOf(1f) }

    Box(modifier = Modifier.fillMaxSize()) {
        MainLayout(maskAlpha)

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

@OptIn(UnstableApi::class)
@Composable
private fun MainLayout(maskAlpha: Float) {
    val context = LocalContext.current
    val engine = remember {
        Engine(getCmdList097(context))
    }
    val videoPlayer = remember {
        ExoPlayer.Builder(context).build().apply {
            repeatMode = ExoPlayer.REPEAT_MODE_ONE
        }
    }
    val audioPlayer = remember {
        ExoPlayer.Builder(context).build().apply {
            repeatMode = ExoPlayer.REPEAT_MODE_ONE
        }
    }
    val currentMovieRes = engine.currentMovieRes.collectAsState(initial = 0).value
    val currentMusicRes = engine.currentMusicRes.collectAsState(initial = 0).value
    val currentSpeaker = engine.currentSpeaker.collectAsState(initial = "").value
    val currentText = engine.currentText.collectAsState(initial = "").value
    var currentDisplayText by remember { mutableStateOf("") }
    var isInTextAnimation by remember { mutableStateOf(true) }
    val nextIconBlinkAlpha = remember { Animatable(1f) }

    DisposableEffect(Unit) {
        onDispose {
            audioPlayer.release()
        }
    }

    Box(modifier = Modifier
        .fillMaxSize()
        .clickable(
            enabled = maskAlpha == 0f && !isInTextAnimation,
            interactionSource = remember { MutableInteractionSource() },
            indication = null
        ) {
            engine.goNext()
        }
    ) {
        AndroidView(
            factory = { ctx ->
                PlayerView(ctx).apply {
                    player = videoPlayer
                    useController = false
                    resizeMode = AspectRatioFrameLayout.RESIZE_MODE_ZOOM
                }
            }
        )

        Column(modifier = Modifier
            .align(Alignment.BottomCenter)
            .padding(bottom = 32.dp)
            .fillMaxWidth(fraction = 0.8f)
        ) {
            if (currentSpeaker != null) {
                Box(modifier = Modifier
                    .width(128.dp)
                    .height(44.dp)
                    .background(Color.Black.copy(alpha = 0.7f), shape = RoundedCornerShape(10.dp))
                ) {
                    Text(
                        text = currentSpeaker,
                        style = FontRegular.copy(color = Color.White, fontSize = 16.sp),
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Box(modifier = Modifier
                .fillMaxWidth()
                .height(156.dp)
                .background(Color.Black.copy(alpha = 0.7f))
                .border(1.dp, color = Color.White)
            ) {
                Text(
                    text = currentDisplayText,
                    style = FontRegular.copy(color = Color.White, fontSize = 16.sp),
                    modifier = Modifier.padding(16.dp)
                )

                Icon(
                    painter = painterResource(R.drawable.ic_frame_corner),
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .size(32.dp)
                        .offset(x = (-16).dp, y = (-16).dp)
                )

                Icon(
                    painter = painterResource(R.drawable.ic_frame_corner),
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .size(32.dp)
                        .offset(x = 16.dp, y = (-16).dp)
                )

                Icon(
                    painter = painterResource(R.drawable.ic_frame_corner),
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .size(32.dp)
                        .offset(x = (-16).dp, y = 16.dp)
                )

                Icon(
                    painter = painterResource(R.drawable.ic_frame_corner),
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .size(32.dp)
                        .offset(x = 16.dp, y = 16.dp)
                )

                Icon(
                    painter = painterResource(R.drawable.ic_frame_center),
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier
                        .align(Alignment.TopCenter)
                        .width(61.dp)
                        .height(28.dp)
                        .offset(y = (-28).dp)
                )

                Icon(
                    painter = painterResource(R.drawable.ic_frame_center),
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .width(61.dp)
                        .height(28.dp)
                        .offset(y = 28.dp)
                        .rotate(180f)
                )

                if (currentText.isNotEmpty() && maskAlpha == 0f && !isInTextAnimation) {
                    Icon(
                        painter = painterResource(R.drawable.ic_triangle),
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier
                            .align(Alignment.BottomEnd)
                            .padding(end = 16.dp, bottom = 16.dp)
                            .size(24.dp)
                            .rotate(180f)
                            .alpha(nextIconBlinkAlpha.value)
                    )
                }
            }
        }
    }

    LaunchedEffect(maskAlpha) {
        if (maskAlpha == 0f) {
            engine.goNext()
        }
    }

    LaunchedEffect(currentMovieRes) {
        if (currentMovieRes != 0) {
            val videoUri = "android.resource://${context.packageName}/${currentMovieRes}".toUri()
            videoPlayer.setMediaItem(MediaItem.fromUri(videoUri))
            videoPlayer.prepare()
            videoPlayer.play()
        } else {
            videoPlayer.stop()
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
            currentDisplayText = ""
            for (i in currentText.indices) {
                currentDisplayText += currentText[i]
                delay(100)
            }
            isInTextAnimation = false
        }
    }

    LaunchedEffect(currentText, maskAlpha, isInTextAnimation) {
        // 只有在需要显示三角时才闪烁
        if (currentText.isNotEmpty() && maskAlpha == 0f && !isInTextAnimation) {
            while (true) {
                nextIconBlinkAlpha.animateTo(
                    targetValue = 0f,
                    animationSpec = tween(durationMillis = 500)
                )
                nextIconBlinkAlpha.animateTo(
                    targetValue = 1f,
                    animationSpec = tween(durationMillis = 500)
                )
            }
        } else {
            nextIconBlinkAlpha.snapTo(1f)
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