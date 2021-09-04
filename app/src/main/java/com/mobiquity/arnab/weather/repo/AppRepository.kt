package com.mobiquity.arnab.weather.repo

import com.mobiquity.arnab.weather.database.AppDatabase
import com.mobiquity.arnab.weather.network.ApiRequest


class AppRepository(val db: AppDatabase, val api: ApiRequest) {
}