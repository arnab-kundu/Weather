package com.mobiquity.arnab.weather.di

import com.mobiquity.arnab.weather.App
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        AppModule::class,
        HomeFragmentModule::class
    ]
)
interface AppComponent {
    fun inject(application: App)
}