package com.drm.to.ssy.digitalletter.ui.making

import android.annotation.SuppressLint
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun MakingScreen() {
    val context = LocalContext.current
    val url = remember {
        "https://www.tang-ping.top/documents?id=100303"
    }

    AndroidView(
        modifier = Modifier.fillMaxSize(),
        factory = { ctx ->
            WebView(context).apply {
                settings.javaScriptEnabled = true
                settings.domStorageEnabled = true
                settings.databaseEnabled = true
                settings.allowFileAccess = true
                settings.allowContentAccess = true
                webViewClient = WebViewClient()
                setLayerType(View.LAYER_TYPE_HARDWARE, null)
                WebView.setWebContentsDebuggingEnabled(true)
                loadUrl(url)
            }
        }
    )
}