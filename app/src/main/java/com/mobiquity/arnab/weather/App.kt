package com.mobiquity.arnab.weather

import android.app.Application
import com.mobiquity.arnab.weather.database.AppDatabase
import com.mobiquity.arnab.weather.network.ApiRequest
import com.mobiquity.arnab.weather.network.NetworkConnectionInterceptor
import com.mobiquity.arnab.weather.repo.AppRepository
import com.mobiquity.arnab.weather.ui.home.HomeViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

class App : Application(), KodeinAware {


    override val kodein: Kodein = Kodein.lazy {
        import(androidXModule(this@App))

        bind() from singleton { NetworkConnectionInterceptor(instance()) }
        bind() from singleton { ApiRequest(instance()) }
        bind() from singleton { AppDatabase(instance()) }
        bind() from singleton { AppRepository(instance(), instance()) }
        bind() from singleton { HomeViewModelFactory(instance()) }

    }

}