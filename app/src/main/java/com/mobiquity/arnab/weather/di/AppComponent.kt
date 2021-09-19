package com.mobiquity.arnab.weather.di

import com.mobiquity.arnab.weather.App
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

//TODO 8
@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        HomeFragmentModule::class,
        SettingsFragmentModule::class,
        AppModule::class
    ]
)
interface AppComponent {
    fun inject(application: App)
}