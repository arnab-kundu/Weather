package com.mobiquity.arnab.weather.repo

import com.mobiquity.arnab.weather.database.AppDatabase
import com.mobiquity.arnab.weather.network.WeatherApi
import javax.inject.Inject


class AppRepository @Inject constructor(val db: AppDatabase, val weatherApi: WeatherApi) {
}