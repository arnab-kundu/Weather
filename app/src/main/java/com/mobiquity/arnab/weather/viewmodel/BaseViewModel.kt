package com.mobiquity.arnab.weather.viewmodel

import androidx.lifecycle.ViewModel
import com.mobiquity.arnab.weather.repo.BaseRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

abstract class BaseViewModel(private val repository: BaseRepository) : ViewModel() {
    suspend fun logout() = withContext(Dispatchers.IO) { repository.logout() }
}