package com.ojoya.allinone.di

import com.ojoya.allinone.ui.dashboard.DashboardActivity
import com.ojoya.allinone.ui.dashboard.tabs.games.hangman.HangmanActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilderModule {

    @ContributesAndroidInjector
    abstract fun contributeDashboardActivity(): DashboardActivity

    @ContributesAndroidInjector
    abstract fun contributeHangmanActivity(): HangmanActivity
}