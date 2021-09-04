package com.mobiquity.arnab.weather.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.mobiquity.arnab.weather.R
import com.mobiquity.arnab.weather.enums.Units
import com.mobiquity.arnab.weather.network.response.ForecastResponse
import com.mobiquity.arnab.weather.utils.Constants
import com.mobiquity.arnab.weather.utils.DateFormatter
import kotlinx.android.synthetic.main.row_forecast.view.*

class ForecastAdapter(val fiveDayForecast: List<ForecastResponse.FiveDay>) : RecyclerView.Adapter<ForecastAdapter.ViewHolder>() {

    private lateinit var ctx: Context
    private lateinit var units: Units

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.row_forecast, parent, false)
        ctx = parent.context
        val sp = ctx.getSharedPreferences(Constants.SP_NAME, AppCompatActivity.MODE_PRIVATE)
        units = if (sp.getString("unit", Units.metric.name) == Units.metric.name) {
            Units.metric
        } else {
            Units.imperial
        }
        return ForecastAdapter.ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.tv_date_time.text = DateFormatter.getFormattedDate(fiveDayForecast[position].dt.toLong()*1000)
        if (units == Units.metric)
            holder.itemView.tv_temp.text = "${fiveDayForecast[position].main.temp.toInt()}°C"
        else
            holder.itemView.tv_temp.text = "${fiveDayForecast[position].main.temp.toInt()}°F"

        holder.itemView.tv_humidity.text = "Humidity: ${fiveDayForecast[position].main.humidity}%"
        holder.itemView.tv_rain_info.text = "Clouds ${fiveDayForecast[position].clouds.all}%"
        holder.itemView.tv_wind_info.text = "Wind speed ${fiveDayForecast[position].wind.speed}km/hr\ntowards ${fiveDayForecast[position].wind.deg}°"

    }

    override fun getItemCount(): Int {
        return fiveDayForecast.size
    }
}