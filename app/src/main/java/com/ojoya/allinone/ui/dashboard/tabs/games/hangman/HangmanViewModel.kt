package com.ojoya.allinone.ui.dashboard.tabs.games.hangman

import androidx.lifecycle.ViewModel
import com.ojoya.allinone.utils.AppSharedPreferences
import javax.inject.Inject

class HangmanViewModel @Inject constructor(private val appSharedPreferences: AppSharedPreferences) : ViewModel() {
    var score: Int = 0
        set(value) {
            field = value
            maxScore = value
        }

    var maxScore: Int = appSharedPreferences.hangmanMaxScore
        get() = appSharedPreferences.hangmanMaxScore
        private set(value) {
            if (value > field) {
                field = value
                appSharedPreferences.hangmanMaxScore = value
            }
        }

    var selectedCategory: Int = HangmanGameFragment.RANDOM
}