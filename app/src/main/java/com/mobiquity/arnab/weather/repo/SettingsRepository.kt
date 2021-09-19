package com.mobiquity.arnab.weather.repo

import android.content.SharedPreferences
import com.mobiquity.arnab.weather.database.AppDatabase
import com.mobiquity.arnab.weather.enums.Units
import com.mobiquity.arnab.weather.network.BaseApi
import com.mobiquity.arnab.weather.network.SafeApiCall
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

// TODO 6
class SettingsRepository
@Inject constructor(
    private val api: BaseApi,
    private val db: AppDatabase,
    private val sp: SharedPreferences
) : BaseRepository(api, db, sp) {


    fun changeUnit(units: Units): String {
        sp.edit().putString("unit", units.name).apply()
        return sp.getString("unit", "").toString()
    }


    fun getSavedUnit(): Units {
        if (sp.getString("unit", "").toString() == Units.metric.name) {
            return Units.metric
        } else if (sp.getString("unit", "").toString() == Units.imperial.name) {
            return Units.imperial
        }
        return Units.metric
    }


    fun resetSavedLocations() {
        CoroutineScope(Dispatchers.IO).launch {
            db.cityDao().truncateTable()
        }
    }

}