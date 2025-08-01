package com.drm.to.ssy.digitalletter.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.drm.to.ssy.digitalletter.R

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)

val FontRegular = TextStyle(
    fontFamily = FontFamily.Default,
    fontWeight = FontWeight.Normal,
)

val FontRegularItalic = TextStyle(
    fontFamily = FontFamily.Default,
    fontWeight = FontWeight.Normal,
    fontStyle = FontStyle.Italic,
)

val FontBold = TextStyle(
    fontFamily = FontFamily.Default,
    fontWeight = FontWeight.Bold,
)

val FontMonospace = TextStyle(
    fontFamily = FontFamily.Monospace,
    fontWeight = FontWeight.Normal
)

val SerifRegular = TextStyle(
    fontFamily = FontFamily(Font(R.font.noto_serif_sc_regular))
)

val SerifBold = TextStyle(
    fontFamily = FontFamily(Font(R.font.noto_serif_sc_bold))
)