package com.drm.to.ssy.digitalletter.ui.resume

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.OnBackPressedCallback
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.drm.to.ssy.digitalletter.R
import com.drm.to.ssy.digitalletter.engine.getCmdList099
import com.drm.to.ssy.digitalletter.model.MemoryConfig
import com.drm.to.ssy.digitalletter.ui.mr.MergeRequestActivity
import com.drm.to.ssy.digitalletter.ui.theme.DigitalLetterTheme
import com.drm.to.ssy.digitalletter.widget.MemoryMonologueScreen

class MemoryOnResumeActivity : ComponentActivity() {
    companion object {
        fun startMe(context: Context) {
            val intent = Intent(context, MemoryOnResumeActivity::class.java)
            context.startActivity(intent)
        }
    }

    /**
     * 2025年7月26日
     * 这是我们的第2次约会
     * 就像Activity在onResume()正式走入可交互阶段一样
     * 你在我心中onResume()
     * 没有打扰、没有中断，我只想把全部注意力都给你
     */
    override fun onResume() {
        super.onResume()
    }

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
                MemoryMonologueScreen(
                    config = MemoryConfig(
                        cmdList = getCmdList099(this),
                        themeColor = androidx.compose.ui.graphics.Color.White,
                        introDrawableRes = R.drawable.img_on_resume_intro,
                    ),
                    onActivityJump = {
                        MergeRequestActivity.startMe(this)
                    }
                )
            }
        }
    }
}