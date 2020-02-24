package com.ojoya.allinone.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ojoya.allinone.di.annotations.ViewModelKey
import com.ojoya.allinone.ui.dashboard.tabs.games.hangman.HangmanViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(HangmanViewModel::class)
    abstract fun bindHangmanViewModel(hangmanViewModel: HangmanViewModel): ViewModel
}