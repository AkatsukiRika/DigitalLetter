package com.drm.to.ssy.digitalletter.ui.staffroll

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import com.drm.to.ssy.digitalletter.R
import com.drm.to.ssy.digitalletter.ui.theme.SerifBold
import com.drm.to.ssy.digitalletter.ui.theme.SerifRegular

@Composable
fun StaffRollScreen() {
    val messages = remember {
        listOf(
            R.string.staff_roll_title,
            R.string.staff_roll_sub_title_approved,
            R.string.empty_line,
            R.string.staff_roll_software_design_title,
            R.string.staff_roll_software_design_00,
            R.string.staff_roll_software_design_01,
            R.string.staff_roll_software_design_02,
            R.string.staff_roll_software_design_03,
            R.string.staff_roll_software_design_04,
            R.string.staff_roll_software_design_05,
            R.string.staff_roll_software_design_06,
            R.string.staff_roll_software_design_07,
            R.string.empty_line,
            R.string.staff_roll_visual_material_title,
            R.string.staff_roll_visual_material_00,
            R.string.staff_roll_visual_material_01,
            R.string.staff_roll_visual_material_02,
            R.string.staff_roll_visual_material_03,
            R.string.staff_roll_visual_material_04,
            R.string.empty_line,
            R.string.staff_roll_audio_material_title,
            R.string.staff_roll_audio_material,
            R.string.empty_line,
            R.string.staff_roll_tools_title,
            R.string.staff_roll_tools,
            R.string.empty_line,
            R.string.staff_roll_special_thanks_title,
            R.string.staff_roll_special_thanks,
            R.string.empty_line,
            R.string.staff_roll_last_message_approved
        )
    }
    val boldMessages = remember {
        listOf(
            R.string.staff_roll_title,
            R.string.staff_roll_sub_title_approved,
            R.string.staff_roll_software_design_title,
            R.string.staff_roll_visual_material_title,
            R.string.staff_roll_audio_material_title,
            R.string.staff_roll_tools_title,
            R.string.staff_roll_special_thanks_title,
            R.string.staff_roll_last_message_approved
        )
    }
    var height by remember { mutableIntStateOf(0) }
    val density = LocalDensity.current
    val pxToDp = with(density) {
        { px: Int ->
            px.toDp()
        }
    }
    val lazyListState = rememberLazyListState()

    Box(modifier = Modifier
        .fillMaxSize()
        .onSizeChanged {
            height = it.height
        }
    ) {
        LazyColumn(
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxWidth(fraction = 0.4f)
                .alpha(if (height > 0) 1f else 0f),
            state = lazyListState,
        ) {
            item {
                Spacer(modifier = Modifier.height(pxToDp(height)))
            }

            items(messages) {
                Text(
                    text = stringResource(it).trim(),
                    style = if (it in boldMessages) {
                        SerifBold.copy(color = Color.White, fontSize = 16.sp)
                    } else {
                        SerifRegular.copy(color = Color.White, fontSize = 16.sp)
                    }
                )
            }

            item {
                Spacer(modifier = Modifier.height(pxToDp(height)))
            }
        }
    }

    LaunchedEffect(height) {
        if (height > 0) {
            lazyListState.scrollToItem(0)
        }
    }
}