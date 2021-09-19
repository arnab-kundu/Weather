package com.mobiquity.arnab.weather.repo

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import com.mobiquity.arnab.weather.database.AppDatabase
import com.mobiquity.arnab.weather.database.entity.CityEntity
import com.mobiquity.arnab.weather.network.BaseApi
import com.mobiquity.arnab.weather.network.SafeApiCall
import javax.inject.Inject


open class BaseRepository
@Inject
constructor(
    private val api: BaseApi,
    private val db: AppDatabase,
    private val sp: SharedPreferences
) : SafeApiCall {

    open suspend fun logout() = safeApiCall {
        api.logout()
    }


    fun observeCityList(searchedText: String): LiveData<List<CityEntity>> {
        return if (searchedText.isNotEmpty()) {
            db.cityDao().searchCityLiveData("$searchedText%")
        } else
            db.cityDao().getCityLiveData()
    }
}