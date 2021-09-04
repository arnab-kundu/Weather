package com.mobiquity.arnab.weather.ui.activity

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.mobiquity.arnab.weather.R
import com.mobiquity.arnab.weather.database.AppDatabase
import com.mobiquity.arnab.weather.enums.Units
import com.mobiquity.arnab.weather.network.ApiRequest
import com.mobiquity.arnab.weather.network.RetrofitClient
import com.mobiquity.arnab.weather.network.response.ForecastResponse
import com.mobiquity.arnab.weather.network.response.WeatherResponse
import com.mobiquity.arnab.weather.ui.adapter.ForecastAdapter
import com.mobiquity.arnab.weather.utils.Constants
import kotlinx.android.synthetic.main.activity_city.*
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CityActivity : AppCompatActivity() {

    private val TAG = "CityActivity"
    lateinit var apiRequest: ApiRequest
    lateinit var latitude: String
    lateinit var longitude: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_city)

        val bandle: Bundle = intent.extras!!
        val city = bandle.get("city")
        tv_city_name.text = city.toString()

        apiRequest = RetrofitClient.retrofitInstance!!.create(ApiRequest::class.java)
        val db: AppDatabase = AppDatabase.invoke(this)
        CoroutineScope(Dispatchers.Main).launch {
            val deferred: Deferred<Unit> = CoroutineScope(Dispatchers.IO).async {
                val cityEntity = db.cityDao().searchCity(city.toString())[0]
                latitude = cityEntity.lat.toString()
                longitude = cityEntity.lon.toString()
            }
            deferred.await()
            //currentWeatherAPI(17.38721272157597.toString(), 78.48014261573553.toString(), Constants.WEATHER_API_KEY)
            currentWeatherAPI(latitude, longitude, Constants.WEATHER_API_KEY)
        }
    }

    override fun onResume() {
        super.onResume()
        forecastAPI(17.38721272157597.toString(), 78.48014261573553.toString(), Constants.WEATHER_API_KEY)
    }

    fun currentWeatherAPI(latitude: String, longitude: String, appid: String, units: Units = Units.metric) {
        apiRequest.getCurrentWeatherReportAPI(
            lat = latitude,
            lon = longitude,
            appid = appid,
            units = units.name
        ).enqueue(object : Callback<WeatherResponse> {
            override fun onResponse(call: Call<WeatherResponse>, response: Response<WeatherResponse>) {
                if (response.isSuccessful) {
                    val weatherResponse: WeatherResponse = response.body()!!

                    tv_city_name.text = weatherResponse.name
                    if (units == Units.metric) {
                        tv_temp.text = "${weatherResponse.main.temp.toInt()}°C"
                    } else if (units == Units.imperial) {
                        tv_temp.text = "${weatherResponse.main.temp.toInt()}°F"
                    }
                    tv_humidity.text = "Humidity: ${weatherResponse.main.humidity}%"
                    tv_rain_info.text = "Rain possibility: Clouds ${weatherResponse.clouds.all}%"
                    tv_wind_info.text = "Wind speed ${weatherResponse.wind.speed}km/hr towards ${weatherResponse.wind.deg}°"
                } else {
                    Toast.makeText(this@CityActivity, resources.getString(R.string.error_msg), Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message}")
                Toast.makeText(this@CityActivity, resources.getString(R.string.error_msg), Toast.LENGTH_LONG).show()
            }
        })
    }

    fun forecastAPI(latitude: String, longitude: String, appid: String, units: Units = Units.metric) {
        apiRequest.getWeatherForecastAPI(
            lat = latitude,
            lon = longitude,
            appid = appid,
            units = units.name
        ).enqueue(object : Callback<ForecastResponse> {
            override fun onResponse(call: Call<ForecastResponse>, response: Response<ForecastResponse>) {
                if (response.isSuccessful) {
                    val forecastResponse: ForecastResponse = response.body()!!
                    val fiveDayForecast: List<ForecastResponse.FiveDay> = forecastResponse.five_day_list
                    val adapter: ForecastAdapter = ForecastAdapter(fiveDayForecast)
                    rv_forecast.layoutManager = LinearLayoutManager(this@CityActivity, LinearLayoutManager.HORIZONTAL, false)
                    rv_forecast.adapter = adapter
                }
            }

            override fun onFailure(call: Call<ForecastResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message}")
                Toast.makeText(this@CityActivity, "Some thing went wrong", Toast.LENGTH_LONG).show()
            }
        })
    }
}