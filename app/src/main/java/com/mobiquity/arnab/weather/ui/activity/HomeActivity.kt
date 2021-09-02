package com.mobiquity.arnab.weather.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.mobiquity.arnab.weather.R

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

    }

    fun city(view: View) {
        val intent = Intent(this, CityActivity::class.java)
        intent.putExtra("city", "Hydrabad")
        startActivity(intent)
    }

    fun bookmarkLocation(view: View) {
        val intent = Intent(this, MapsActivity::class.java)
        startActivity(intent)
    }
}