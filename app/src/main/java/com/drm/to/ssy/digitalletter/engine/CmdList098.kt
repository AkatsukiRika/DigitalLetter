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
        },

        SceneInitCmd().apply {
            title = "Ver 0.9.8 C"
            movieRes = R.raw.ver_098_bga_2
            musicRes = R.raw.ver_098_bgm_2
        },
        DialogCmd().apply {
            speaker = context.getString(R.string.character_1_state_1)
            text = context.getString(R.string.ver_098_c_00)
        },
        DialogCmd().apply {
            speaker = context.getString(R.string.character_0_state_2)
            text = context.getString(R.string.ver_098_c_01)
        },
        DialogCmd().apply {
            text = context.getString(R.string.ver_098_c_02)
        },
        DialogCmd().apply {
            speaker = context.getString(R.string.character_1_state_1)
            text = context.getString(R.string.ver_098_c_03)
        },
        DialogCmd().apply {
            speaker = context.getString(R.string.character_0_state_2)
            text = context.getString(R.string.ver_098_c_04)
        },
        DialogCmd().apply {
            speaker = context.getString(R.string.character_1_state_1)
            text = context.getString(R.string.ver_098_c_05)
        },
        DialogCmd().apply {
            text = context.getString(R.string.ver_098_c_06)
        },
        EllipsisCmd().apply {
            count = 6
        },
        DialogCmd().apply {
            speaker = context.getString(R.string.character_0_state_2)
            text = context.getString(R.string.ver_098_c_07)
        },
        DialogCmd().apply {
            text = context.getString(R.string.ver_098_c_08)
        },
        MonologueCmd().apply {
            text = context.getString(R.string.ver_098_c_09)
        },
        MonologueCmd().apply {
            text = context.getString(R.string.ver_098_c_10)
        },

        SceneInitCmd().apply {
            title = "Ver 0.9.8 D"
            movieRes = R.raw.ver_098_bga_3
            musicRes = R.raw.ver_098_bgm_3
        },
        MonologueCmd().apply {
            text = context.getString(R.string.ver_098_d_00)
        },
        MonologueCmd().apply {
            text = context.getString(R.string.ver_098_d_01)
        },
        MonologueCmd().apply {
            text = context.getString(R.string.ver_098_d_02)
        },
        MonologueCmd().apply {
            text = context.getString(R.string.ver_098_d_03)
        },
        MonologueCmd().apply {
            text = context.getString(R.string.ver_098_d_04)
        },
        MonologueCmd().apply {
            text = context.getString(R.string.ver_098_d_05)
        },

        SceneInitCmd().apply {
            title = "Ver 0.9.8 E"
            movieRes = R.raw.ver_098_bga_4
        },
        MonologueCmd().apply {
            text = context.getString(R.string.ver_098_e_00)
        },
        MonologueCmd().apply {
            text = context.getString(R.string.ver_098_e_01)
        },
        DialogCmd().apply {
            speaker = context.getString(R.string.character_0_state_2)
            text = context.getString(R.string.ver_098_e_02)
        },
        DialogCmd().apply {
            speaker = context.getString(R.string.character_1_state_1)
            text = context.getString(R.string.ver_098_e_03)
        },
        MonologueCmd().apply {
            text = context.getString(R.string.ver_098_e_04)
        },
        MonologueCmd().apply {
            text = context.getString(R.string.ver_098_e_05)
        },

        SceneInitCmd().apply {
            title = "Ver 0.9.8 F"
            movieRes = R.raw.ver_098_bga_5
            musicRes = R.raw.ver_098_bgm_4
        },
        DialogCmd().apply {
            speaker = context.getString(R.string.character_1_state_1)
            text = context.getString(R.string.ver_098_f_00)
        },
        DialogCmd().apply {
            speaker = context.getString(R.string.character_0_state_2)
            text = context.getString(R.string.ver_098_f_01)
        },
        DialogCmd().apply {
            text = context.getString(R.string.ver_098_f_02)
        },
        DialogCmd().apply {
            text = context.getString(R.string.ver_098_f_03)
        },
        DialogCmd().apply {
            speaker = context.getString(R.string.character_1_state_1)
            text = context.getString(R.string.ver_098_f_04)
        },
        DialogCmd().apply {
            speaker = context.getString(R.string.character_0_state_2)
            text = context.getString(R.string.ver_098_f_05)
        },
        MonologueCmd().apply {
            text = context.getString(R.string.ver_098_f_06)
        },
        MonologueCmd().apply {
            text = context.getString(R.string.ver_098_f_07)
        },
        MonologueCmd().apply {
            text = context.getString(R.string.ver_098_f_08)
        },
        DialogCmd().apply {
            speaker = context.getString(R.string.character_1_state_1)
            text = context.getString(R.string.ver_098_f_09)
        },
        DialogCmd().apply {
            text = context.getString(R.string.ver_098_f_10)
        },
        DialogCmd().apply {
            text = context.getString(R.string.ver_098_f_11)
        },
        DialogCmd().apply {
            text = context.getString(R.string.ver_098_f_12)
        },
        DialogCmd().apply {
            speaker = context.getString(R.string.character_0_state_2)
            text = context.getString(R.string.ver_098_f_13)
        },
        DialogCmd().apply {
            text = context.getString(R.string.ver_098_f_14)
        },
        DialogCmd().apply {
            text = context.getString(R.string.ver_098_f_15)
        }
    )
}