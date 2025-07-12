package com.drm.to.ssy.digitalletter.utils

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit

object SharedPrefUtils {
    private const val PREF_NAME = "digital_letter_prefs"
    const val KEY_APPROVE_STATUS = "approve_status"

    /**
     * 获取 SharedPreferences 实例
     */
    private fun getSharedPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    }

    /**
     * 存储整数
     */
    fun saveInt(context: Context, key: String, value: Int) {
        getSharedPreferences(context).edit { putInt(key, value) }
    }

    /**
     * 读取整数
     */
    fun getInt(context: Context, key: String, defaultValue: Int = 0): Int {
        return getSharedPreferences(context).getInt(key, defaultValue)
    }
}