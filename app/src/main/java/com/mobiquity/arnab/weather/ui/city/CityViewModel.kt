package com.mobiquity.arnab.weather.ui.city

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mobiquity.arnab.weather.enums.Units
import com.mobiquity.arnab.weather.network.WeatherApi
import com.mobiquity.arnab.weather.network.RetrofitClient
import com.mobiquity.arnab.weather.network.response.WeatherResponse
import com.mobiquity.arnab.weather.repo.AppRepository
import com.mobiquity.arnab.weather.utils.Constants
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class CityViewModel @Inject constructor(private val repo: AppRepository) : ViewModel() {
    private val TAG = "CityViewModel"

    var mNameString: MutableLiveData<String> = MutableLiveData<String>()
    var mTempString: MutableLiveData<String> = MutableLiveData<String>()
    var mHumidityString: MutableLiveData<String> = MutableLiveData<String>()
    var mRainString: MutableLiveData<String> = MutableLiveData<String>()
    var mWindString: MutableLiveData<String> = MutableLiveData<String>()

    var nameString: LiveData<String> = mNameString
    var tempString: LiveData<String> = mTempString
    var humidityString: LiveData<String> = mHumidityString
    var rainString: LiveData<String> = mRainString
    var windString: LiveData<String> = mWindString

    var weatherApi: WeatherApi

    init {
        weatherApi = RetrofitClient.retrofitInstance!!.create(WeatherApi::class.java)
        currentWeatherAPI("0.0", "0.0", Constants.WEATHER_API_KEY)
    }

    fun currentWeatherAPI(latitude: String, longitude: String, appid: String, units: Units = Units.metric) {
        weatherApi.getCurrentWeatherReportAPI(
            lat = latitude,
            lon = longitude,
            appid = appid,
            units = units.name
        ).enqueue(object : Callback<WeatherResponse> {
            override fun onResponse(call: Call<WeatherResponse>, response: Response<WeatherResponse>) {
                if (response.isSuccessful) {

                    val weatherResponse: WeatherResponse = response.body()!!


                    mNameString.postValue(weatherResponse.name)

                    if (units == Units.metric) {
                        mTempString.postValue("${weatherResponse.main.temp.toInt()}°C")
                    } else if (units == Units.imperial) {
                        mTempString.postValue("${weatherResponse.main.temp.toInt()}°F")
                    }
                    mHumidityString.postValue("Humidity: ${weatherResponse.main.humidity}%")
                    mRainString.postValue("Rain possibility: Clouds ${weatherResponse.clouds.all}%")
                    mWindString.postValue("Wind speed ${weatherResponse.wind.speed}km/hr towards ${weatherResponse.wind.deg}°")
                }
            }

            override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message}")
            }
        })
    }

}