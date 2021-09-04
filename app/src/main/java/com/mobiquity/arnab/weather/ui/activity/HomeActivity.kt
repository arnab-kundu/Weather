package com.mobiquity.arnab.weather.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.mobiquity.arnab.weather.R
import com.mobiquity.arnab.weather.database.AppDatabase
import com.mobiquity.arnab.weather.database.entity.CityEntity
import com.mobiquity.arnab.weather.ui.adapter.CityListAdapter
import com.mobiquity.arnab.weather.utils.Constants
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.coroutines.*

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)


        CoroutineScope(Dispatchers.Main).launch {
            CoroutineScope(Dispatchers.IO).async {
                val db: AppDatabase = AppDatabase.invoke(this@HomeActivity)
                CoroutineScope(Dispatchers.Main).launch {
                    val deferred: Deferred<List<CityEntity>> = CoroutineScope(Dispatchers.IO).async {
                        val cityList: List<CityEntity> = db.cityDao().all
                        cityList
                    }
                    val cityList: List<CityEntity> = deferred.await()
                    /*val adapter: CityListAdapter = CityListAdapter(cityList)
                    rv_city_list.layoutManager = LinearLayoutManager(this@HomeActivity)
                    rv_city_list.adapter = adapter*/
                }
            }
        }
    }

    fun bookmarkLocation(view: View) {
        val intent = Intent(this, MapsActivity::class.java)
        startActivity(intent)
    }
}