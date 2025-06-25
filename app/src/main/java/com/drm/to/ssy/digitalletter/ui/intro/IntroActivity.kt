package com.drm.to.ssy.digitalletter.ui.intro

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.drm.to.ssy.digitalletter.ui.create.MemoryOnCreateActivity
import com.drm.to.ssy.digitalletter.ui.theme.DigitalLetterTheme

class IntroActivity : ComponentActivity() {
    companion object {
        fun startMe(context: Context) {
            val intent = Intent(context, IntroActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.dark(scrim = Color.TRANSPARENT),
            navigationBarStyle = SystemBarStyle.dark(scrim = Color.TRANSPARENT)
        )
        setContent {
            DigitalLetterTheme {
                IntroScreen(onContinue = {
                    MemoryOnCreateActivity.startMe(this)
                })
            }
        }
    }
}