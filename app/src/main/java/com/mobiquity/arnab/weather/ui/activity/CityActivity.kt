package com.mobiquity.arnab.weather.ui.activity

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.mobiquity.arnab.weather.R
import com.mobiquity.arnab.weather.network.ApiRequest
import com.mobiquity.arnab.weather.network.RetrofitClient
import com.mobiquity.arnab.weather.network.response.ForecastResponse
import com.mobiquity.arnab.weather.network.response.WeatherResponse
import com.mobiquity.arnab.weather.utils.Constants
import kotlinx.android.synthetic.main.activity_city.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CityActivity : AppCompatActivity() {

    private val TAG = "CityActivity"
    lateinit var apiRequest: ApiRequest

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_city)

        val bandle: Bundle = intent.extras!!
        val city = bandle.get("city")
        tv_city.text = city.toString()

        apiRequest = RetrofitClient.retrofitInstance!!.create(ApiRequest::class.java)
    }

    fun forecastAPI() {
        apiRequest.getWeatherForecastAPI(
            lat = "0",
            lon = "0",
            appid = Constants.appid
        ).enqueue(object : Callback<ForecastResponse> {
            override fun onResponse(call: Call<ForecastResponse>, response: Response<ForecastResponse>) {
                Log.d(TAG, "city: ${response.body()!!.city.name}")
                Toast.makeText(this@CityActivity, "${response.body()!!.city.name}", Toast.LENGTH_LONG).show()
            }

            override fun onFailure(call: Call<ForecastResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message}")
                Toast.makeText(this@CityActivity, "Some thing went wrong", Toast.LENGTH_LONG).show()
            }
        })
    }


    fun weather() {
        apiRequest.getCurrentWeatherReportAPI(
            lat = "0",
            lon = "0",
            appid = Constants.appid
        ).enqueue(object : Callback<WeatherResponse> {
            override fun onResponse(call: Call<WeatherResponse>, response: Response<WeatherResponse>) {
                Toast.makeText(this@CityActivity, "${response.body()!!.name}", Toast.LENGTH_LONG).show()
            }

            override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message}")
                Toast.makeText(this@CityActivity, "Some thing went wrong", Toast.LENGTH_LONG).show()
            }
        })
    }
}