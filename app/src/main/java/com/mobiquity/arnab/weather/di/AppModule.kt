package com.mobiquity.arnab.weather.di

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.mobiquity.arnab.weather.database.AppDatabase
import com.mobiquity.arnab.weather.network.BaseApi
import com.mobiquity.arnab.weather.network.RemoteDataSource
import com.mobiquity.arnab.weather.utils.Constants
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

//TODO 7
@Module
class AppModule(private val context: Context) {

    @Singleton
    @Provides
    fun provideBaseApi(remoteDataSource: RemoteDataSource): BaseApi {
        return remoteDataSource.buildApi(BaseApi::class.java, context)
    }

    @Singleton
    @Provides
    fun provideSettingsPreferences(): SharedPreferences {
        return context.getSharedPreferences(Constants.SP_NAME, MODE_PRIVATE)!!
    }

    @Singleton
    @Provides
    fun provideAppDatabase(): AppDatabase {
        return AppDatabase.invoke(context)
    }
}