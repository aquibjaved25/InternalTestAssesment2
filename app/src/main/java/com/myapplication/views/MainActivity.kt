package com.myapplication.views

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.*
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.assignmentmvvm.viewmodel.MainViewModel
import com.retrofit.utility.Utils
import androidx.lifecycle.Observer
import com.myapplication.R
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


class MainActivity : AppCompatActivity() {
    private val TAG = "PermissionDemo"
    private val LOCATION_REQUEST_CODE = 101
    private lateinit var mainViewModel: MainViewModel
    var zipCode: String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        title= "Current Location Weather"
        //Check for permission
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            setupPermissions()
            return
        } else {
            getCurrentCountryCity()
        }

        btn_week_forecast.setOnClickListener {
            val intent = Intent(this,WeakWeatherForecastActivity::class.java)
            intent.putExtra("ZipCode",zipCode)
            startActivity(intent)

        }
    }

    private fun setupPermissions() {
        val permission = ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION)

        if (permission != PackageManager.PERMISSION_GRANTED) {
            Log.i(TAG, "Permission to Location denied")
            makeRequest()
        }
    }

    private fun makeRequest() {
        ActivityCompat.requestPermissions(this,
                arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION),
                LOCATION_REQUEST_CODE)
    }

    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            LOCATION_REQUEST_CODE -> {

                if (grantResults.isEmpty() || grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Permission hasn't been granted by user", Toast.LENGTH_LONG).show()
                } else {

                    getCurrentCountryCity()
                }
            }
        }
    }

    fun getCurrentCountryCity() {

        val lm = getSystemService(Context.LOCATION_SERVICE) as LocationManager

        val criteria = Criteria()
        val bestProvider: String? = lm.getBestProvider(criteria, false)
        val location: Location?
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            setupPermissions()
            return
        } else {
            location = lm.getLastKnownLocation(bestProvider!!)
        }

        val geocoder = Geocoder(this, Locale.getDefault())
        val addresses: List<Address> = geocoder.getFromLocation(location.latitude, location.longitude, 1)
        // val cityName: String = addresses[0].getAddressLine(0)
        val cityName: String = addresses[0].locality
        val countryName: String = addresses[0].countryCode
         zipCode = addresses[0].postalCode

        //Toast.makeText(this, cityName + "," + countryName, Toast.LENGTH_LONG).show()

        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        registerObservers()
        if (Utils.hasNetworkAvailable(this)) {

            mainViewModel.getTodos(cityName + "," + countryName)

            // getCoroutineData()
        } else {
            Toast.makeText(this, "Please connect to internet first", Toast.LENGTH_LONG).show()
        }
    }

    private fun registerObservers(){
        mainViewModel.usersSuccessLiveData.observe(this, Observer {
            weatherList -> weatherList?.let {
            weather.text=it.weather[0].description
            temperature.text=it.main.temp.toString()
            pressure.text=it.main.pressure.toString()
            humidity.text=it.main.humidity.toString()
        }
        } )

        mainViewModel.usersFailureLiveData.observe(this, Observer { isFailed ->
            isFailed?.let {
                Toast.makeText(this, "Oops! something went wrong", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
