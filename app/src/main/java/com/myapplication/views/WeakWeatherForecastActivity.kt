package com.myapplication.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.assignmentmvvm.viewmodel.MainViewModel
import com.myapplication.R
import com.myapplication.model.weak.List
import com.myapplication.viewmodel.WeakForecastViewModel
import com.retrofit.adapter.WeatherForecastAdapter
import com.retrofit.utility.Utils
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_week_weather_forecast.*

class WeakWeatherForecastActivity : AppCompatActivity() {

    private lateinit var weakForecastViewModel: WeakForecastViewModel
    private lateinit var layoutManager: RecyclerView.LayoutManager
    var WeatherForecastdataList = ArrayList<List>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_week_weather_forecast)

        //actionbar
        val actionbar = supportActionBar
        //set actionbar title
        actionbar!!.title = "Weak Report"
        //set back button
        actionbar.setDisplayHomeAsUpEnabled(true)
        actionbar.setDisplayHomeAsUpEnabled(true)


        val ZipCode = intent.getStringExtra("ZipCode")

        layoutManager = LinearLayoutManager(this)
        rv_weak_forecast_list.layoutManager = layoutManager

        rv_weak_forecast_list.adapter = WeatherForecastAdapter(this, WeatherForecastdataList)

        weakForecastViewModel = ViewModelProvider(this).get(WeakForecastViewModel::class.java)
        registerObservers()

        if (Utils.hasNetworkAvailable(this)) {

            weakForecastViewModel.getTodos(ZipCode!!)

            // getCoroutineData()
        } else {
            Toast.makeText(this, "Please connect to internet first", Toast.LENGTH_LONG).show()
        }
    }

    private fun registerObservers() {
        weakForecastViewModel.usersSuccessLiveData.observe(this, Observer { weatherList ->
            weatherList?.let {

                WeatherForecastdataList.clear()
                WeatherForecastdataList.addAll(it.list)
                rv_weak_forecast_list.adapter?.notifyDataSetChanged()

                // Toast.makeText(this, "It worked", Toast.LENGTH_SHORT).show()
            }
        })

        weakForecastViewModel.usersFailureLiveData.observe(this, Observer { isFailed ->
            isFailed?.let {
                Toast.makeText(this, "Oops! something went wrong", Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}
