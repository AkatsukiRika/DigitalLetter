package com.drm.to.ssy.digitalletter.engine

import android.content.Context
import com.drm.to.ssy.digitalletter.R

fun getCmdList098(context: Context): List<IEngineCmd> {
    return listOf(
        SceneInitCmd().apply {
            title = "Ver 0.9.8 A"
            movieRes = R.raw.ver_098_bga_0
            musicRes = R.raw.ver_098_bgm_0
        },
        DialogCmd().apply {
            speaker = context.getString(R.string.character_0_state_2)
            text = context.getString(R.string.ver_098_a_00)
        },
        DialogCmd().apply {
            speaker = context.getString(R.string.character_1_state_1)
            text = context.getString(R.string.ver_098_a_01)
        },
        DialogCmd().apply {
            text = context.getString(R.string.ver_098_a_02)
        },
        DialogCmd().apply {
            text = context.getString(R.string.ver_098_a_03)
        },
        DialogCmd().apply {
            speaker = context.getString(R.string.character_0_state_2)
            text = context.getString(R.string.ver_098_a_04)
        },
        DialogCmd().apply {
            text = context.getString(R.string.ver_098_a_05)
        },
        DialogCmd().apply {
            speaker = context.getString(R.string.character_1_state_1)
            text = context.getString(R.string.ver_098_a_06)
        },
        DialogCmd().apply {
            text = context.getString(R.string.ver_098_a_07)
        },
        DialogCmd().apply {
            speaker = context.getString(R.string.character_0_state_2)
            text = context.getString(R.string.ver_098_a_08)
        },
        DialogCmd().apply {
            text = context.getString(R.string.ver_098_a_09)
        },
        DialogCmd().apply {
            speaker = context.getString(R.string.character_1_state_1)
            text = context.getString(R.string.ver_098_a_10)
        },

        SceneInitCmd().apply {
            title = "Ver 0.9.8 B"
            movieRes = R.raw.ver_098_bga_1
            musicRes = R.raw.ver_098_bgm_1
        },
        DialogCmd().apply {
            speaker = context.getString(R.string.character_0_state_2)
            text = context.getString(R.string.ver_098_b_00)
        },
        DialogCmd().apply {
            text = context.getString(R.string.ver_098_b_01)
        },
        DialogCmd().apply {
            speaker = context.getString(R.string.character_1_state_1)
            text = context.getString(R.string.ver_098_b_02)
        },
        DialogCmd().apply {
            text = context.getString(R.string.ver_098_b_03)
        },
        DialogCmd().apply {
            speaker = context.getString(R.string.character_0_state_2)
            text = context.getString(R.string.ver_098_b_04)
        },
        DialogCmd().apply {
            speaker = context.getString(R.string.character_0_state_2)
            text = context.getString(R.string.ver_098_b_05)
        },
        DialogCmd().apply {
            speaker = context.getString(R.string.character_1_state_1)
            text = context.getString(R.string.ver_098_b_06)
        },
        DialogCmd().apply {
            text = context.getString(R.string.ver_098_b_07)
        },
        MonologueCmd().apply {
            text = context.getString(R.string.ver_098_b_08)
        },
        MonologueCmd().apply {
            text = context.getString(R.string.ver_098_b_09)
        },
        MonologueCmd().apply {
            text = context.getString(R.string.ver_098_b_10)
        },
        MonologueCmd().apply {
            text = context.getString(R.string.ver_098_b_11)
        }
    )
}