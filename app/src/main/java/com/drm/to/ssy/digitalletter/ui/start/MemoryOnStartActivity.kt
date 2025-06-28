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
import com.drm.to.ssy.digitalletter.ui.theme.DigitalLetterTheme

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
        onBackPressedDispatcher.addCallback(object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                // 屏蔽返回键
            }
        })
        setContent {
            DigitalLetterTheme {

            }
        }
    }
}
