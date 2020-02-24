package com.ojoya.allinone.di

import com.ojoya.allinone.ui.dashboard.tabs.games.hangman.HangmanGameFragment
import com.ojoya.allinone.ui.dashboard.tabs.games.hangman.InitialConfigFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class HangmanFragmentModule {

    @ContributesAndroidInjector
    abstract fun contributeHangmanGameFragment(): HangmanGameFragment

    @ContributesAndroidInjector
    abstract fun contributeInitialConfigFragment(): InitialConfigFragment
}