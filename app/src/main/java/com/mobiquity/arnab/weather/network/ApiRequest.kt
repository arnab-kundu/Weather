package com.mobiquity.arnab.weather.network

import com.mobiquity.arnab.weather.network.response.ForecastResponse
import com.mobiquity.arnab.weather.network.response.WeatherResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


/**
 * Retrofit interface for API call.
 */
interface ApiRequest {

    @GET("data/2.5/weather?")
    fun getCurrentWeatherReportAPI(
        @Query("lat") lat: String?,
        @Query("lon") lon: String?,
        @Query("appid") appid: String?
    ): Call<WeatherResponse>


    @GET("data/2.5/forecast?")
    fun getWeatherForecastAPI(
        @Query("lat") lat: String?,
        @Query("lon") lon: String?,
        @Query("appid") appid: String?
    ): Call<ForecastResponse>
}