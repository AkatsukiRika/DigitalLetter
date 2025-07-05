package com.drm.to.ssy.digitalletter.utils

import android.os.Build
import androidx.media3.exoplayer.ExoPlayer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

/**
 * 获取当前 Android 系统版本号
 */
fun getAndroidVersion(): String {
    return Build.VERSION.RELEASE ?: "Unknown"
}

/**
 * 获取当前屏幕分辨率（宽x高）
 * @param context Context
 * @return 例如 "1080x1920"
 */
fun getScreenResolution(context: android.content.Context): String {
    val displayMetrics = context.resources.displayMetrics
    val width = displayMetrics.widthPixels
    val height = displayMetrics.heightPixels
    return "${width}x${height}"
}

suspend fun performAudioFadeOut(audioPlayer: ExoPlayer, afterCallback: (() -> Unit)? = null) {
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
        audioPlayer.release()
        afterCallback?.invoke()
    }
}
