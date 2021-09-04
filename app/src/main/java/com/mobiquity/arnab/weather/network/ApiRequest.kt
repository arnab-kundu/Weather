package com.mobiquity.arnab.weather.network

import com.mobiquity.arnab.weather.network.response.ForecastResponse
import com.mobiquity.arnab.weather.network.response.WeatherResponse
import com.mobiquity.arnab.weather.utils.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


/**
 * Retrofit interface for API call.
 */
interface ApiRequest {

    @GET("data/2.5/weather?")
    fun getCurrentWeatherReportAPI(
        @Query("lat") lat: String,
        @Query("lon") lon: String,
        @Query("appid") appid: String,
        @Query("units") units: String
    ): Call<WeatherResponse>


    @GET("data/2.5/forecast?")
    fun getWeatherForecastAPI(
        @Query("lat") lat: String,
        @Query("lon") lon: String,
        @Query("appid") appid: String,
        @Query("units") units: String
    ): Call<ForecastResponse>


    companion object {
        operator fun invoke(networkConnectionInterceptor: NetworkConnectionInterceptor): ApiRequest {

            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY
            val httpClient = OkHttpClient.Builder()
            httpClient.addInterceptor(logging)

            return Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .client(httpClient.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiRequest::class.java)
        }
    }
}