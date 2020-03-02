package com.myapplication.repository

import androidx.lifecycle.MutableLiveData
import com.myapplication.model.weak.WeatherForecastModel
import com.myapplication.utility.Constants
import com.myapplication.viewmodel.WeakForecastViewModel
import kotlincodes.com.retrofitwithkotlin.retrofit.ApiClient
import retrofit2.HttpException

class WeakForecastRepository {

    val usersSuccessLiveData = MutableLiveData<WeatherForecastModel>()
    val usersFailureLiveData = MutableLiveData<Boolean>()


    suspend fun getCoroutineData( countryCity:String) {

        val service = ApiClient.getRetrofit()
        //CoroutineScope(Dispatchers.IO).launch {
        val response = service.getForecast(countryCity, Constants.APIKEY)
        //    withContext(Dispatchers.Main) {
        try {
            if (response.isSuccessful) {
                //Do something with response e.g show to the UI.
                usersSuccessLiveData.postValue(response.body())

            } else {
                usersFailureLiveData.postValue(true)
            }
        } catch (e: HttpException) {
            usersFailureLiveData.postValue(true)
        } catch (e: Throwable) {
            usersFailureLiveData.postValue(true)
        }
        //   }
        // }

    }
}