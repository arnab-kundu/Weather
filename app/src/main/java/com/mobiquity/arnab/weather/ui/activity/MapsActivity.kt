package com.mobiquity.arnab.weather.ui.activity

import android.location.Geocoder
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.GoogleMap.OnMapClickListener
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.snackbar.Snackbar
import com.mobiquity.arnab.weather.R
import com.mobiquity.arnab.weather.database.AppDatabase
import com.mobiquity.arnab.weather.database.entity.CityEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {
    private val TAG = "MapsActivity"
    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)


    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera
        val hyderabad = LatLng(17.38721272157597, 78.48014261573553)
        mMap.addMarker(MarkerOptions().position(hyderabad).title("Marker at Hyderabad"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(hyderabad))
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(hyderabad, 15.0f))


        mMap.setOnMapClickListener(OnMapClickListener { point ->
            //allPoints.add(it)
            mMap.clear()
            //mMap.addMarker(MarkerOptions().position(point))
            Log.d(TAG, "onMapReady: ${point.latitude} ${point.longitude}")

            val geocoder: Geocoder = Geocoder(this)
            val list = geocoder.getFromLocation(point.latitude, point.longitude, 1)
            Log.d(TAG, "onMapReady: ${list[0].locality}")
            if (list[0].locality == null) {
                mMap.addMarker(MarkerOptions().position(point))
                Toast.makeText(this, "Not data found for selected location", Toast.LENGTH_SHORT).show()
            } else {
                mMap.addMarker(MarkerOptions().position(point).title(list[0].locality))
                Toast.makeText(this, "${list[0].locality} added to bookmark", Toast.LENGTH_SHORT).show()
                // TODO DATABASE operation

                val db: AppDatabase = AppDatabase.invoke(this)
                CoroutineScope(Dispatchers.IO).launch {
                    db.cityDao().insert(
                        CityEntity(
                            id = 0,
                            list[0].locality,
                            lat = point.latitude,
                            lon = point.longitude
                        )
                    )
                }
            }

        })
    }
}