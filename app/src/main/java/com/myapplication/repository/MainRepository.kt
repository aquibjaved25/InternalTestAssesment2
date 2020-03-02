package com.assignmentmvvm.repository

import androidx.lifecycle.MutableLiveData
import com.myapplication.model.main.WeatherModel
import com.myapplication.utility.Constants
import kotlincodes.com.retrofitwithkotlin.retrofit.ApiClient
import retrofit2.HttpException

class MainRepository {

    val usersSuccessLiveData = MutableLiveData<WeatherModel>()
    val usersFailureLiveData = MutableLiveData<Boolean>()


       suspend fun getCoroutineData( countryCity:String) {

        val service = ApiClient.getRetrofit()
        //CoroutineScope(Dispatchers.IO).launch {
            val response = service.getWeather(countryCity,Constants.APIKEY)
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