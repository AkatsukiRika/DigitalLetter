package com.drm.to.ssy.digitalletter.ui.intro

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.OnBackPressedCallback
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.drm.to.ssy.digitalletter.ui.create.MemoryOnCreateActivity
import com.drm.to.ssy.digitalletter.ui.theme.DigitalLetterTheme

class IntroActivity : ComponentActivity() {
    companion object {
        fun startMe(context: Context) {
            val intent = Intent(context, IntroActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_SINGLE_TOP
            context.startActivity(intent)
        }
    }

    private var shouldRestartAudio by mutableStateOf(false)

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
                IntroScreen(
                    shouldRestartAudio = shouldRestartAudio,
                    onContinue = {
                        MemoryOnCreateActivity.startMe(this)
                    },
                    onExit = {
                        finish()
                    }
                )
            }
        }
    }

    override fun onResume() {
        super.onResume()
        shouldRestartAudio = true
    }

    override fun onPause() {
        super.onPause()
        shouldRestartAudio = false
    }
}