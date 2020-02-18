package com.ojoya.allinone.di

import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component( modules = [AndroidInjectionModule::class, ActivityBuilderModule::class, AppModule::class])
interface AppComponent {
    fun inject(app: App)
}