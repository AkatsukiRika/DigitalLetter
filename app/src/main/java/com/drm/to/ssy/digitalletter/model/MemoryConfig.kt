package com.drm.to.ssy.digitalletter.model

import androidx.compose.foundation.layout.BoxScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.drm.to.ssy.digitalletter.engine.IEngineCmd

data class MemoryConfig(
    val cmdList: List<IEngineCmd>,
    val themeColor: Color,
    val introDrawableRes: Int,
    val frameDecoration: @Composable BoxScope.() -> Unit = {}
)