package com.mobiquity.arnab.weather.ui.home

import androidx.lifecycle.*
import com.mobiquity.arnab.weather.App
import com.mobiquity.arnab.weather.database.AppDatabase
import com.mobiquity.arnab.weather.database.entity.CityEntity
import com.mobiquity.arnab.weather.repo.AppRepository
import kotlinx.coroutines.*

class HomeViewModel(private val repository: AppRepository) : AndroidViewModel(App()) {

    val mCityList: MutableLiveData<List<CityEntity>> = MutableLiveData<List<CityEntity>>()
    var cityList: LiveData<List<CityEntity>> = mCityList

    var searchedText: String = ""

    fun observeCityList(): LiveData<List<CityEntity>> {
        return if (searchedText.isNotEmpty()) {
            repository.db.cityDao().searchCityLiveData("$searchedText%")
        } else
            repository.db.cityDao().getCityLiveData()

    }

    init {

        viewModelScope.launch {
            CoroutineScope(Dispatchers.IO).async {
                val db: AppDatabase = repository.db
                CoroutineScope(Dispatchers.Main).launch {
                    val deferred: Deferred<LiveData<List<CityEntity>>> = CoroutineScope(Dispatchers.IO).async {
                        val cityList: LiveData<List<CityEntity>> = db.cityDao().getCityLiveData()
                        cityList
                    }
                    this@HomeViewModel.cityList = deferred.await()
                }
            }
        }

        CoroutineScope(Dispatchers.IO).launch {
           repository.db.cityDao().insert(
                CityEntity(
                    id = 0,
                    "kolkataasda",
                    lat = 0.005,
                    lon = 2.026
                )
            )
        }
    }
}