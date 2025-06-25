package com.drm.to.ssy.digitalletter.ui.intro

import androidx.annotation.OptIn
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import com.drm.to.ssy.digitalletter.R
import androidx.core.net.toUri
import androidx.media3.common.MediaItem
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.AspectRatioFrameLayout
import androidx.media3.ui.PlayerView
import com.drm.to.ssy.digitalletter.ui.theme.FontRegular
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@OptIn(UnstableApi::class)
@Composable
fun IntroScreen(onContinue: () -> Unit) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val videoPlayer = remember {
        val videoUri = "android.resource://${context.packageName}/${R.raw.intro}".toUri()
        ExoPlayer.Builder(context).build().apply {
            val mediaItem = MediaItem.fromUri(videoUri)
            setMediaItem(mediaItem)
            prepare()
            repeatMode = ExoPlayer.REPEAT_MODE_ONE
            playWhenReady = true
        }
    }
    val audioPlayer = remember {
        val audioUri = "android.resource://${context.packageName}/${R.raw.intro_bgm}".toUri()
        ExoPlayer.Builder(context).build().apply {
            val mediaItem = MediaItem.fromUri(audioUri)
            setMediaItem(mediaItem)
            prepare()
            repeatMode = ExoPlayer.REPEAT_MODE_ONE
            playWhenReady = true
        }
    }
    var fadeOutJob by remember { mutableStateOf<Job?>(null) }

    DisposableEffect(Unit) {
        onDispose {
            fadeOutJob?.cancel()
            videoPlayer.release()
            audioPlayer.release()
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        AndroidView(
            factory = { ctx ->
                PlayerView(ctx).apply {
                    player = videoPlayer
                    useController = false
                    resizeMode = AspectRatioFrameLayout.RESIZE_MODE_ZOOM
                }
            },
            modifier = Modifier.fillMaxSize()
        )

        Box(modifier = Modifier
            .align(Alignment.Center)
            .border(1.dp, color = Color.White, shape = RoundedCornerShape(20.dp))
            .clip(RoundedCornerShape(20.dp))
            .clickable {
                fadeOutJob?.cancel()
                fadeOutJob = scope.launch {
                    val fadeDurationMs = 1000L
                    val initialVolume = audioPlayer.volume
                    val steps = 20
                    val volumeStep = initialVolume / steps
                    val delayTime = fadeDurationMs / steps

                    var currentVolume = initialVolume
                    for (i in 0 until steps) {
                        currentVolume -= volumeStep
                        audioPlayer.volume = currentVolume.coerceAtLeast(0f)
                        delay(delayTime)
                    }

                    audioPlayer.volume = 0f

                    withContext(Dispatchers.Main) {
                        videoPlayer.release()
                        audioPlayer.release()
                        onContinue()
                    }
                }
            }
        ) {
            Text(
                text = stringResource(R.string.text_intro_button),
                style = FontRegular.copy(color = Color.White, fontSize = 16.sp),
                modifier = Modifier.padding(vertical = 10.dp, horizontal = 20.dp)
            )
        }
    }
}