package com.drm.to.ssy.digitalletter.ui.making

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.drm.to.ssy.digitalletter.ui.theme.DigitalLetterTheme
import com.drm.to.ssy.digitalletter.utils.hideSystemUI

class MakingActivity : ComponentActivity() {
    companion object {
        fun startMe(context: Context) {
            val intent = Intent(context, MakingActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.dark(scrim = Color.TRANSPARENT),
            navigationBarStyle = SystemBarStyle.dark(scrim = Color.TRANSPARENT)
        )
        hideSystemUI(window)
        setContent {
            DigitalLetterTheme {
                MakingScreen()
            }
        }
    }
}