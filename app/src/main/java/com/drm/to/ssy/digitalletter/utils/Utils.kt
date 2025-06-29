package com.drm.to.ssy.digitalletter.utils

import android.os.Build

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
