package com.drm.to.ssy.digitalletter.ui.appendix

import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.net.toUri
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import com.drm.to.ssy.digitalletter.R
import com.drm.to.ssy.digitalletter.model.AppendixInfo
import com.drm.to.ssy.digitalletter.ui.theme.FontBold
import com.drm.to.ssy.digitalletter.ui.theme.FontRegular
import com.drm.to.ssy.digitalletter.ui.theme.FontRegularItalic

@Composable
fun AppendixScreen() {
    val context = LocalContext.current
    val infoList = remember {
        listOf(
            AppendixInfo(
                resId = R.raw.dancehall_self_cover,
                name = context.getString(R.string.appendix_info_name_0),
                desc = context.getString(R.string.appendix_info_desc_0)
            ),
            AppendixInfo(
                resId = R.raw.approved_ed,
                name = context.getString(R.string.appendix_info_name_1),
                desc = context.getString(R.string.appendix_info_desc_1)
            ),
            AppendixInfo(
                resId = R.raw.reverted_ed,
                name = context.getString(R.string.appendix_info_name_2),
                desc = context.getString(R.string.appendix_info_desc_2)
            ),
        )
    }
    var currentInfoItem by remember { mutableStateOf<AppendixInfo?>(null) }
    val player = remember {
        ExoPlayer.Builder(context).build()
    }

    DisposableEffect(Unit) {
        onDispose {
            player.release()
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Icon(
            painter = painterResource(R.drawable.ic_back),
            contentDescription = null,
            tint = Color.Unspecified,
            modifier = Modifier
                .align(Alignment.TopStart)
                .statusBarsPadding()
                .clickable(interactionSource = remember { MutableInteractionSource() }, indication = null) {
                    (context as? Activity)?.finish()
                }
                .padding(16.dp)
                .size(24.dp)
        )

        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(modifier = Modifier.weight(1f)) {
                ContentSelectArea(
                    modifier = Modifier.align(Alignment.Center),
                    list = infoList,
                    onClick = {
                        currentInfoItem = it
                    }
                )
            }

            ContentArea(
                modifier = Modifier.aspectRatio(3 / 4f),
                exoPlayer = player,
                currentInfoItem = currentInfoItem
            )

            Box(modifier = Modifier.weight(1f)) {
                Text(
                    text = currentInfoItem?.desc ?: "",
                    style = FontRegular.copy(color = Color.White, fontSize = 14.sp),
                    modifier = Modifier.align(Alignment.Center),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Composable
private fun ContentArea(modifier: Modifier = Modifier, exoPlayer: ExoPlayer, currentInfoItem: AppendixInfo?) {
    val context = LocalContext.current

    Box(modifier = modifier
        .background(Color.Black)
    ) {
        AndroidView(
            factory = { ctx ->
                PlayerView(ctx).apply {
                    player = exoPlayer
                    useController = true
                }
            },
            modifier = Modifier.fillMaxSize()
        )

        if (currentInfoItem == null) {
            Text(
                text = stringResource(R.string.tip_select_content),
                style = FontBold.copy(color = Color.White, fontSize = 16.sp),
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }

    LaunchedEffect(currentInfoItem) {
        if (currentInfoItem != null) {
            val uri = "android.resource://${context.packageName}/${currentInfoItem.resId}".toUri()
            val mediaItem = MediaItem.fromUri(uri)
            exoPlayer.setMediaItem(mediaItem)
            exoPlayer.prepare()
            exoPlayer.playWhenReady = true
        } else {
            exoPlayer.stop()
        }
    }
}

@Composable
private fun ContentSelectArea(modifier: Modifier = Modifier, list: List<AppendixInfo>, onClick: (AppendixInfo) -> Unit) {
    LazyColumn(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(list) {
            Button(
                modifier = Modifier.padding(bottom = 16.dp),
                onClick = {
                    onClick(it)
                }
            ) {
                Text(
                    text = it.name,
                    style = FontRegularItalic.copy(color = Color.White, fontSize = 14.sp)
                )
            }
        }
    }
}