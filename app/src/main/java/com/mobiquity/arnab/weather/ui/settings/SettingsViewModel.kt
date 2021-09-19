package com.mobiquity.arnab.weather.ui.settings

import com.mobiquity.arnab.weather.enums.Units
import com.mobiquity.arnab.weather.repo.SettingsRepository
import com.mobiquity.arnab.weather.viewmodel.BaseViewModel
import javax.inject.Inject

// TODO 5
class SettingsViewModel @Inject constructor(private val repository: SettingsRepository) : BaseViewModel(repository) {



    fun changeUnit(units: Units) {
        repository.changeUnit(units)
    }

    fun getSavedUnit(): Units {
        return repository.getSavedUnit()
    }

    fun resetSavedCities() {
        repository.resetSavedLocations()
    }

}