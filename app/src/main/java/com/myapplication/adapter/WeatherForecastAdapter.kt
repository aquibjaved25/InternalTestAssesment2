package com.retrofit.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.myapplication.R
import com.myapplication.model.weak.List
import com.myapplication.views.WeakWeatherForecastActivity
import kotlinx.android.synthetic.main.item_weather.view.*

class WeatherForecastAdapter(
    private val context: WeakWeatherForecastActivity,
    private val weatherForecastList: ArrayList<List>
) :
    RecyclerView.Adapter<WeatherForecastAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.item_weather,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return weatherForecastList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.weather?.text = weatherForecastList.get(position).weather[0].description
        holder.temperature?.text = weatherForecastList.get(position).main.temp.toString()
        holder.pressure?.text = weatherForecastList.get(position).main.pressure.toString()
        holder.humidity?.text = weatherForecastList.get(position).main.humidity.toString()
        holder.time?.text = weatherForecastList.get(position).dtTxt.toString()

        holder.itemView.setOnClickListener {
            //Toast.makeText(context, chaptersList.get(position), Toast.LENGTH_LONG).show()
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val weather = view.weather
        val temperature = view.temperature
        val pressure = view.pressure
        val humidity = view.humidity
        val time =view.time
    }
}
