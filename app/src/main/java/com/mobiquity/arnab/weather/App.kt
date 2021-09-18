package com.mobiquity.arnab.weather

import android.app.Application
import com.mobiquity.arnab.weather.di.AppModule
import com.mobiquity.arnab.weather.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class App : Application(), HasAndroidInjector {


    @Inject
    lateinit var mInjector: DispatchingAndroidInjector<Any>

    override fun onCreate() {
        super.onCreate()
        DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
            .inject(this)
    }


    override fun androidInjector(): AndroidInjector<Any> {
        return mInjector
    }
}