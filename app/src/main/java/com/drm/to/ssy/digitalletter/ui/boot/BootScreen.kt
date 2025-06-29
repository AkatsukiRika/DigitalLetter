package com.drm.to.ssy.digitalletter.ui.boot

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.drm.to.ssy.digitalletter.R
import com.drm.to.ssy.digitalletter.utils.getAndroidVersion
import com.drm.to.ssy.digitalletter.utils.getScreenResolution
import com.drm.to.ssy.digitalletter.ui.theme.ColorBrand
import com.drm.to.ssy.digitalletter.ui.theme.FontRegular
import kotlinx.coroutines.delay

@Composable
fun BootScreen(onFinish: () -> Unit) {
    val context = LocalContext.current
    val bootLogList = remember {
        listOf(
            context.getString(R.string.boot_log_0),
            context.getString(R.string.boot_log_1),
            context.getString(R.string.boot_log_2),
            context.getString(R.string.boot_log_3),
            context.getString(R.string.boot_log_4),
            context.getString(R.string.boot_log_5),
            context.getString(R.string.boot_log_6),
            context.getString(R.string.boot_log_7),
            context.getString(R.string.boot_log_8),
            context.getString(R.string.boot_log_9),
            context.getString(R.string.boot_log_10),
            context.getString(R.string.boot_log_11),
            context.getString(R.string.boot_log_12),
            context.getString(R.string.boot_log_13),
            context.getString(R.string.boot_log_14)
        )
    }
    var currentIndex by remember { mutableIntStateOf(0) }
    var currentSubIndex by remember { mutableIntStateOf(0) }
    var currentLog by remember { mutableStateOf("") }

    LaunchedEffect(currentIndex, currentSubIndex) {
        val newLog = StringBuilder("")
        for (index in 0..currentIndex) {
            val newLine = when (index) {
                4 -> {
                    bootLogList[index].format(getAndroidVersion())
                }

                5 -> {
                    bootLogList[index].format(getScreenResolution(context))
                }

                6, 7 -> {
                    if (currentIndex == index) {
                        bootLogList[index].format(currentSubIndex.subIndexToLetter())
                    } else {
                        bootLogList[index].format("Done!")
                    }
                }

                8, 9, 10, 11, 12 -> {
                    if (currentIndex == index) {
                        val face = when (currentIndex) {
                            8 -> "o(≧v≦)o"
                            9 -> "(///▽///)"
                            10 -> "( ; _ ; )"
                            11 -> "♪(´ε｀ )"
                            else -> "‧₊ ꒰ა ✞ ໒꒱˖ ⊹"
                        }

                        when (currentSubIndex) {
                            0 -> bootLogList[index].format("...")
                            1 -> bootLogList[index].format(face)
                            else -> bootLogList[index].format("Done!")
                        }
                    } else {
                        bootLogList[index].format("Done!")
                    }
                }

                14 -> {
                    if (currentIndex == index) {
                        bootLogList[index].format(3 - currentSubIndex)
                    } else {
                        bootLogList[index].format(0)
                    }
                }

                else -> {
                    if (index in bootLogList.indices) {
                        bootLogList[index]
                    } else {
                        onFinish()
                        ""
                    }
                }
            }
            newLog.appendLine(newLine)
        }
        currentLog = newLog.toString()
    }

    LaunchedEffect(Unit) {
        while (currentIndex <= bootLogList.lastIndex) {
            delay(1000)
            when (currentIndex) {
                6, 7 -> {
                    while (currentSubIndex + 1 <= 6) {
                        currentSubIndex++
                        Log.d("BootScreen", "currentSubIndex: $currentSubIndex")
                        delay(1000)
                    }
                    currentIndex++
                    currentSubIndex = 0
                    Log.d("BootScreen", "currentIndex: $currentIndex, currentSubIndex: $currentSubIndex")
                }

                8, 9, 10, 11, 12 -> {
                    while (currentSubIndex + 1 <= 2) {
                        currentSubIndex++
                        Log.d("BootScreen", "currentSubIndex: $currentSubIndex")
                        delay(1000)
                    }
                    currentIndex++
                    currentSubIndex = 0
                    Log.d("BootScreen", "currentIndex: $currentIndex, currentSubIndex: $currentSubIndex")
                }

                14 -> {
                    while (currentSubIndex + 1 <= 3) {
                        currentSubIndex++
                        Log.d("BootScreen", "currentSubIndex: $currentSubIndex")
                        delay(1000)
                    }
                    currentIndex++
                    currentSubIndex = 0
                    Log.d("BootScreen", "currentIndex: $currentIndex, currentSubIndex: $currentSubIndex")
                }

                else -> {
                    currentIndex++
                    currentSubIndex = 0
                    Log.d("BootScreen", "currentIndex: $currentIndex, currentSubIndex: $currentSubIndex")
                }
            }
        }
    }

    Box(modifier = Modifier
        .fillMaxSize()
        .statusBarsPadding()
    ) {
        Icon(
            painter = painterResource(R.drawable.ic_heart),
            contentDescription = null,
            tint = ColorBrand.copy(alpha = 0.25f),
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxSize(fraction = 0.8f)
        )

        Text(
            text = currentLog,
            style = FontRegular.copy(color = ColorBrand, fontSize = 16.sp),
            modifier = Modifier.padding(16.dp)
        )
    }
}

fun Int.subIndexToLetter() = when (this) {
    0 -> "A"
    1 -> "B"
    2 -> "C"
    3 -> "D"
    4 -> "E"
    5 -> "F"
    else -> "Done!"
}