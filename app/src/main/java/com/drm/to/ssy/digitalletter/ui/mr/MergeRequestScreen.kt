package com.drm.to.ssy.digitalletter.ui.mr

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.core.net.toUri
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import com.drm.to.ssy.digitalletter.R

@Composable
fun MergeRequestScreen() {
    val context = LocalContext.current
    val audioPlayer = remember {
        ExoPlayer.Builder(context).build().apply {
            repeatMode = ExoPlayer.REPEAT_MODE_ONE
        }
    }

    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color.White)
    ) {
    }

    LaunchedEffect(Unit) {
        val audioUri = "android.resource://${context.packageName}/${R.raw.dancehall}".toUri()
        audioPlayer.setMediaItem(MediaItem.fromUri(audioUri))
        audioPlayer.prepare()
        audioPlayer.play()
    }
}