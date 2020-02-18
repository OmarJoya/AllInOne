package com.ojoya.allinone.utils

import android.content.SharedPreferences
import javax.inject.Inject

class AppSharedPreferences @Inject constructor(private val sharedPreferences: SharedPreferences) {
    var hangmanMaxScore: Int
        get() = sharedPreferences.getInt("hangmanMaxScore", 0)
        set(value) = sharedPreferences.edit().putInt("hangmanMaxScore", value).apply()
}