package kotlincodes.com.retrofitwithkotlin.retrofit

import com.myapplication.model.main.WeatherModel
import com.myapplication.model.weak.WeatherForecastModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    // its for coroutine
    @GET("weather")
    suspend fun getWeather(
        @Query("q") one:String,
        @Query("APPID") key:String
    ): Response<WeatherModel>

    @GET("forecast")
    suspend fun getForecast(
        @Query("zip") one:String,
        @Query("appid") key:String
    ): Response<WeatherForecastModel>

    // its for Retrofit
//    @GET("todos")
//     fun getTodos(): Call<List<TodoDataModel>>

}