package com.mobiquity.arnab.weather.di

import com.mobiquity.arnab.weather.ui.home.HomeFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class HomeFragmentModule {

    @ContributesAndroidInjector
    abstract fun contributeLoginFragmentInjector(): HomeFragment

}