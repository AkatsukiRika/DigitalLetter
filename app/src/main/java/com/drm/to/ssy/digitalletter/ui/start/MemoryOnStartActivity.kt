package com.drm.to.ssy.digitalletter.ui.start

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.OnBackPressedCallback
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.drm.to.ssy.digitalletter.R
import com.drm.to.ssy.digitalletter.engine.getCmdList098
import com.drm.to.ssy.digitalletter.model.MemoryConfig
import com.drm.to.ssy.digitalletter.ui.resume.MemoryOnResumeActivity
import com.drm.to.ssy.digitalletter.ui.theme.ColorOnStartTheme
import com.drm.to.ssy.digitalletter.ui.theme.DigitalLetterTheme
import com.drm.to.ssy.digitalletter.utils.hideSystemUI
import com.drm.to.ssy.digitalletter.widget.MemoryDialogScreen

class MemoryOnStartActivity : ComponentActivity() {
    companion object {
        fun startMe(context: Context) {
            val intent = Intent(context, MemoryOnStartActivity::class.java)
            context.startActivity(intent)
        }
    }

    /**
     * 2025年6月7日 15:00~23:00
     * 这是我们的第1次约会
     * Activity在onStart()时正在被启动，而我的感情，就像此时的Activity对用户可见一样，已经对你可见。
     */
    override fun onStart() {
        super.onStart()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.dark(scrim = Color.TRANSPARENT),
            navigationBarStyle = SystemBarStyle.dark(scrim = Color.TRANSPARENT)
        )
        hideSystemUI(window)
        onBackPressedDispatcher.addCallback(object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                // 屏蔽返回键
            }
        })
        setContent {
            DigitalLetterTheme {
                MemoryDialogScreen(
                    config = MemoryConfig(
                        cmdList = getCmdList098(this),
                        themeColor = ColorOnStartTheme,
                        introDrawableRes = R.drawable.img_on_start_intro,
                        frameDecoration = {
                            Icon(
                                painter = painterResource(R.drawable.ic_on_start_frame_deco),
                                contentDescription = null,
                                tint = androidx.compose.ui.graphics.Color.White,
                                modifier = Modifier
                                    .align(Alignment.TopStart)
                                    .size(32.dp)
                                    .offset(x = (-24).dp, y = (-24).dp)
                            )

                            Icon(
                                painter = painterResource(R.drawable.ic_on_start_frame_deco),
                                contentDescription = null,
                                tint = androidx.compose.ui.graphics.Color.White,
                                modifier = Modifier
                                    .align(Alignment.TopEnd)
                                    .size(32.dp)
                                    .offset(x = 24.dp, y = (-24).dp)
                                    .rotate(90f)
                            )

                            Icon(
                                painter = painterResource(R.drawable.ic_on_start_frame_deco),
                                contentDescription = null,
                                tint = androidx.compose.ui.graphics.Color.White,
                                modifier = Modifier
                                    .align(Alignment.BottomStart)
                                    .size(32.dp)
                                    .offset(x = (-24).dp, y = 24.dp)
                                    .scale(scaleX = 1f, scaleY = -1f)
                            )

                            Icon(
                                painter = painterResource(R.drawable.ic_on_start_frame_deco),
                                contentDescription = null,
                                tint = androidx.compose.ui.graphics.Color.White,
                                modifier = Modifier
                                    .align(Alignment.BottomEnd)
                                    .size(32.dp)
                                    .offset(x = 24.dp, y = 24.dp)
                                    .scale(scaleX = 1f, scaleY = -1f)
                                    .rotate(90f)
                            )
                        }
                    ),
                    onActivityJump = {
                        MemoryOnResumeActivity.startMe(this)
                    }
                )
            }
        }
    }
}
