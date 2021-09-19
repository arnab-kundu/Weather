package com.mobiquity.arnab.weather.ui.home

import androidx.lifecycle.LiveData
import com.mobiquity.arnab.weather.database.entity.CityEntity
import com.mobiquity.arnab.weather.repo.BaseRepository
import com.mobiquity.arnab.weather.viewmodel.BaseViewModel
import javax.inject.Inject

class HomeViewModel @Inject constructor(private val repository: BaseRepository) : BaseViewModel(repository) {


    fun observeCityList(searchedText: String): LiveData<List<CityEntity>> {
        return repository.observeCityList(searchedText)
    }
}