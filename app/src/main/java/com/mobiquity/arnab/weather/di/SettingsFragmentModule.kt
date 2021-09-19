package com.mobiquity.arnab.weather.di

import com.mobiquity.arnab.weather.ui.settings.SettingsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

//TODO
@Module
abstract class SettingsFragmentModule {

    @ContributesAndroidInjector
    abstract fun contributeLoginFragmentInjector(): SettingsFragment

}