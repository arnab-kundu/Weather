package com.mobiquity.arnab.weather.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mobiquity.arnab.weather.repo.AppRepository

@Suppress("UNCHECKED_CAST")
class HomeViewModelFactory(private val repository: AppRepository) :
    ViewModelProvider.NewInstanceFactory() {


    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return HomeViewModel(repository) as T
    }
}