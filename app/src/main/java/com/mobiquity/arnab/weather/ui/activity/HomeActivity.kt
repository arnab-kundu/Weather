package com.mobiquity.arnab.weather.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.mobiquity.arnab.weather.R
import com.mobiquity.arnab.weather.database.AppDatabase
import com.mobiquity.arnab.weather.database.entity.CityEntity
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.coroutines.*

@Deprecated(message = "Noy in use any more")
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