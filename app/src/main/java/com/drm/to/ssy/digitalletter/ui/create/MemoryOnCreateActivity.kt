package com.drm.to.ssy.digitalletter.ui.create

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.OnBackPressedCallback
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.drm.to.ssy.digitalletter.R
import com.drm.to.ssy.digitalletter.engine.getCmdList097
import com.drm.to.ssy.digitalletter.model.MemoryConfig
import com.drm.to.ssy.digitalletter.ui.start.MemoryOnStartActivity
import com.drm.to.ssy.digitalletter.ui.theme.ColorOnCreateTheme
import com.drm.to.ssy.digitalletter.ui.theme.DigitalLetterTheme
import com.drm.to.ssy.digitalletter.widget.MemoryDialogScreen

class MemoryOnCreateActivity : ComponentActivity() {
    companion object {
        fun startMe(context: Context) {
            val intent = Intent(context, MemoryOnCreateActivity::class.java)
            context.startActivity(intent)
        }
    }

    /**
     * 2025年5月4日 18:00~20:00
     * 这是我们的第0次约会
     * 就像Activity页面在onCreate()诞生一样，我们的故事，就从这里开始初始化。
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.dark(scrim = Color.TRANSPARENT),
            navigationBarStyle = SystemBarStyle.dark(scrim = Color.TRANSPARENT)
        )
        onBackPressedDispatcher.addCallback(object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                // 屏蔽返回键
            }
        })
        setContent {
            DigitalLetterTheme {
                MemoryDialogScreen(
                    config = MemoryConfig(
                        cmdList = getCmdList097(this),
                        themeColor = ColorOnCreateTheme,
                        introDrawableRes = R.drawable.img_on_create_intro,
                        frameDecoration = {
                            Icon(
                                painter = painterResource(R.drawable.ic_frame_corner),
                                contentDescription = null,
                                tint = androidx.compose.ui.graphics.Color.White,
                                modifier = Modifier
                                    .align(Alignment.TopStart)
                                    .size(32.dp)
                                    .offset(x = (-16).dp, y = (-16).dp)
                            )

                            Icon(
                                painter = painterResource(R.drawable.ic_frame_corner),
                                contentDescription = null,
                                tint = androidx.compose.ui.graphics.Color.White,
                                modifier = Modifier
                                    .align(Alignment.TopEnd)
                                    .size(32.dp)
                                    .offset(x = 16.dp, y = (-16).dp)
                            )

                            Icon(
                                painter = painterResource(R.drawable.ic_frame_corner),
                                contentDescription = null,
                                tint = androidx.compose.ui.graphics.Color.White,
                                modifier = Modifier
                                    .align(Alignment.BottomStart)
                                    .size(32.dp)
                                    .offset(x = (-16).dp, y = 16.dp)
                            )

                            Icon(
                                painter = painterResource(R.drawable.ic_frame_corner),
                                contentDescription = null,
                                tint = androidx.compose.ui.graphics.Color.White,
                                modifier = Modifier
                                    .align(Alignment.BottomEnd)
                                    .size(32.dp)
                                    .offset(x = 16.dp, y = 16.dp)
                            )

                            Icon(
                                painter = painterResource(R.drawable.ic_frame_center),
                                contentDescription = null,
                                tint = androidx.compose.ui.graphics.Color.White,
                                modifier = Modifier
                                    .align(Alignment.TopCenter)
                                    .width(61.dp)
                                    .height(28.dp)
                                    .offset(y = (-28).dp)
                            )

                            Icon(
                                painter = painterResource(R.drawable.ic_frame_center),
                                contentDescription = null,
                                tint = androidx.compose.ui.graphics.Color.White,
                                modifier = Modifier
                                    .align(Alignment.BottomCenter)
                                    .width(61.dp)
                                    .height(28.dp)
                                    .offset(y = 28.dp)
                                    .rotate(180f)
                            )
                        }
                    ),
                    onActivityJump = {
                        MemoryOnStartActivity.startMe(this)
                    }
                )
            }
        }
    }
}

