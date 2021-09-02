package com.mobiquity.arnab.weather.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mobiquity.arnab.weather.R
import com.mobiquity.arnab.weather.network.response.ForecastResponse
import kotlinx.android.synthetic.main.activity_city.*
import kotlinx.android.synthetic.main.row_forecast.view.*

class ForecastAdapter(val fiveDayForecast: List<ForecastResponse.FiveDay>) : RecyclerView.Adapter<ForecastAdapter.ViewHolder>() {

    private lateinit var ctx: Context

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.row_forecast, parent, false)
        ctx = parent.context
        return ForecastAdapter.ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.tv_date_time.text = fiveDayForecast[position].dt_txt
        holder.itemView.tv_temp.text = "${fiveDayForecast[position].main.temp.toInt()}°C"

        holder.itemView.tv_humidity.text = "Humidity: ${fiveDayForecast[position].main.humidity}%"
        holder.itemView.tv_rain_info.text = "Clouds ${fiveDayForecast[position].clouds.all}%"
        holder.itemView.tv_wind_info.text = "Wind speed ${fiveDayForecast[position].wind.speed}km/hr\ntowards ${fiveDayForecast[position].wind.deg}°"

    }

    override fun getItemCount(): Int {
        return fiveDayForecast.size
    }
}