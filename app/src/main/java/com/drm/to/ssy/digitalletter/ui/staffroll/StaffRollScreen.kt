package com.drm.to.ssy.digitalletter.ui.staffroll

import android.app.Activity
import androidx.annotation.OptIn
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.net.toUri
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import com.drm.to.ssy.digitalletter.R
import com.drm.to.ssy.digitalletter.ui.intro.IntroActivity
import com.drm.to.ssy.digitalletter.ui.mr.APPROVE_STATUS_APPROVED
import com.drm.to.ssy.digitalletter.ui.mr.APPROVE_STATUS_REVERTED
import com.drm.to.ssy.digitalletter.utils.SharedPrefUtils
import kotlinx.coroutines.delay

@OptIn(UnstableApi::class)
@Composable
fun StaffRollScreen() {
    val context = LocalContext.current
    val videoPlayer = remember {
        ExoPlayer.Builder(context).build().apply {
            repeatMode = ExoPlayer.REPEAT_MODE_OFF
            playWhenReady = false
        }
    }

    DisposableEffect(videoPlayer) {
        val listener = object : Player.Listener {
            override fun onPlaybackStateChanged(playbackState: Int) {
                if (playbackState == Player.STATE_ENDED) {
                    IntroActivity.startMe(context)
                    (context as? Activity)?.finish()
                }
            }
        }
        videoPlayer.addListener(listener)
        onDispose {
            videoPlayer.removeListener(listener)
            videoPlayer.release()
        }
    }

    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.Black)
    ) {
        AndroidView(
            factory = { ctx ->
                PlayerView(ctx).apply {
                    player = videoPlayer
                    useController = false
                }
            },
            modifier = Modifier
                .aspectRatio(16 / 9f)
                .align(Alignment.Center)
        )
    }

    LaunchedEffect(Unit) {
        delay(100)
        val approveStatus = SharedPrefUtils.getInt(context, SharedPrefUtils.KEY_APPROVE_STATUS, APPROVE_STATUS_REVERTED)
        val videoUri = if (approveStatus == APPROVE_STATUS_APPROVED) {
            "android.resource://${context.packageName}/${R.raw.approved_ed}".toUri()
        } else {
            "android.resource://${context.packageName}/${R.raw.reverted_ed}".toUri()
        }
        videoPlayer.setMediaItem(MediaItem.fromUri(videoUri))
        videoPlayer.prepare()
        videoPlayer.playWhenReady = true
    }
}