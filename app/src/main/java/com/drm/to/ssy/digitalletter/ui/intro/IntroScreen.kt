package com.drm.to.ssy.digitalletter.ui.intro

import androidx.annotation.OptIn
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
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
import com.drm.to.ssy.digitalletter.ui.appendix.AppendixActivity
import com.drm.to.ssy.digitalletter.ui.making.MakingActivity
import com.drm.to.ssy.digitalletter.ui.mr.APPROVE_STATUS_PENDING
import com.drm.to.ssy.digitalletter.ui.theme.FontRegularItalic
import com.drm.to.ssy.digitalletter.utils.SharedPrefUtils
import com.drm.to.ssy.digitalletter.utils.ToastUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@OptIn(UnstableApi::class)
@Composable
fun IntroScreen(
    shouldRestartAudio: Boolean,
    onContinue: () -> Unit,
    onExit: () -> Unit
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val videoPlayer = remember {
        val videoUri = "android.resource://${context.packageName}/${R.raw.intro}".toUri()
        ExoPlayer.Builder(context).build().apply {
            val mediaItem = MediaItem.fromUri(videoUri)
            setMediaItem(mediaItem)
            prepare()
            repeatMode = ExoPlayer.REPEAT_MODE_ONE
            playWhenReady = false
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

    LaunchedEffect(Unit) {
        delay(100)
        videoPlayer.playWhenReady = true
    }

    LaunchedEffect(shouldRestartAudio) {
        if (shouldRestartAudio) {
            fadeOutJob?.cancel()
            audioPlayer.volume = 1f
            audioPlayer.playWhenReady = true
            videoPlayer.playWhenReady = true
        }
    }

    fun isUnlocked(): Boolean {
        val approveStatus = SharedPrefUtils.getInt(context, SharedPrefUtils.KEY_APPROVE_STATUS, APPROVE_STATUS_PENDING)
        return approveStatus != APPROVE_STATUS_PENDING
    }

    fun onButtonClick(action: () -> Unit) {
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
                action()
            }
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

        Column(modifier = Modifier.align(Alignment.Center)) {
            Box(modifier = Modifier
                .border(1.dp, color = Color.White, shape = RoundedCornerShape(4.dp))
                .clip(RoundedCornerShape(4.dp))
                .width(158.dp)
                .height(58.dp)
                .clickable {
                    onButtonClick(onContinue)
                }
            ) {
                Text(
                    text = stringResource(R.string.text_intro_button),
                    style = FontRegularItalic.copy(color = Color.White, fontSize = 20.sp),
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(vertical = 10.dp, horizontal = 20.dp)
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            Box(modifier = Modifier
                .border(1.dp, color = Color.White, shape = RoundedCornerShape(4.dp))
                .clip(RoundedCornerShape(4.dp))
                .width(158.dp)
                .height(58.dp)
                .clickable {
                    if (!isUnlocked()) {
                        ToastUtils.showToast(context, context.getString(R.string.msg_not_unlocked))
                    } else {
                        onButtonClick {
                            AppendixActivity.startMe(context)
                        }
                    }
                }
            ) {
                Text(
                    text = stringResource(R.string.text_appendix_button),
                    style = FontRegularItalic.copy(color = Color.White, fontSize = 20.sp),
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(vertical = 10.dp, horizontal = 20.dp)
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            Box(modifier = Modifier
                .border(1.dp, color = Color.White, shape = RoundedCornerShape(4.dp))
                .clip(RoundedCornerShape(4.dp))
                .width(158.dp)
                .height(58.dp)
                .clickable {
                    if (!isUnlocked()) {
                        ToastUtils.showToast(context, context.getString(R.string.msg_not_unlocked))
                    } else {
                        onButtonClick {
                            MakingActivity.startMe(context)
                        }
                    }
                }
            ) {
                Text(
                    text = stringResource(R.string.text_make_button),
                    style = FontRegularItalic.copy(color = Color.White, fontSize = 20.sp),
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(vertical = 10.dp, horizontal = 20.dp)
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            Box(modifier = Modifier
                .border(1.dp, color = Color.White, shape = RoundedCornerShape(4.dp))
                .clip(RoundedCornerShape(4.dp))
                .width(158.dp)
                .height(58.dp)
                .clickable {
                    onButtonClick(onExit)
                }
            ) {
                Text(
                    text = stringResource(R.string.text_exit_button),
                    style = FontRegularItalic.copy(color = Color.White, fontSize = 20.sp),
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(vertical = 10.dp, horizontal = 20.dp)
                )
            }
        }
    }
}