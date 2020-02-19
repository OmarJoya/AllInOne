package com.ojoya.allinone.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.ojoya.allinone.utils.AppSharedPreferences
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    fun provideContext(application: Application): Context = application

    @Provides
    @Singleton
    fun provideSharedPreferences(context: Context): SharedPreferences = context.getSharedPreferences("SharedPreferences", Context.MODE_PRIVATE)

    @Provides
    @Singleton
    fun provideAppSharePreferences(sharedPreferences: SharedPreferences): AppSharedPreferences = AppSharedPreferences(sharedPreferences)
}