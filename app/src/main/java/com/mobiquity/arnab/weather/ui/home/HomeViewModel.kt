package com.mobiquity.arnab.weather.ui.home

import androidx.lifecycle.LiveData
import com.mobiquity.arnab.weather.database.entity.CityEntity
import com.mobiquity.arnab.weather.repo.BaseRepository
import com.mobiquity.arnab.weather.viewmodel.BaseViewModel
import javax.inject.Inject

class HomeViewModel @Inject constructor(private val repository: BaseRepository) : BaseViewModel(repository) {

    //val mCityList: MutableLiveData<List<CityEntity>> = MutableLiveData<List<CityEntity>>()
    //var cityList: LiveData<List<CityEntity>> = mCityList

    //  var searchedText: String = ""


    fun observeCityList(searchedText: String): LiveData<List<CityEntity>> {
        return repository.observeCityList(searchedText)
    }
}