package com.mobiquity.arnab.weather.ui.city

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mobiquity.arnab.weather.repo.AppRepository

@Suppress("UNCHECKED_CAST")
class CityViewModelFactory(private val repository: AppRepository) :
    ViewModelProvider.NewInstanceFactory() {


    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CityViewModelFactory(repository) as T
    }
}