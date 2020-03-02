package com.myapplication.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myapplication.repository.WeakForecastRepository
import kotlinx.coroutines.launch

class WeakForecastViewModel : ViewModel() {

    private val weakForecastRepository = WeakForecastRepository()

    val usersSuccessLiveData = weakForecastRepository.usersSuccessLiveData
    val usersFailureLiveData = weakForecastRepository.usersFailureLiveData

    fun getTodos(country: String) {
        //this is coroutine viewmodel
        //mainRepository.getCoroutineData()

        //this is coroutine viewmodel scope to call suspend fun of repo.
        // ViewModel includes a built-in viewModelScope.
        // This provides a standard way to launch coroutines within the scope of the ViewModel

        viewModelScope.launch { weakForecastRepository.getCoroutineData(country) }
    }
}