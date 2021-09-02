package com.mobiquity.arnab.weather.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

/**
 * This class is responsible for creating `accelerometer_log` table in database
 */
@Entity(tableName = "city", indices = [Index(value = ["name"], unique = true)])
data class CityEntity(

        @PrimaryKey(autoGenerate = true)
        var id: Long = 0,

        @ColumnInfo(name = "name")
        var name: String = "",

        @ColumnInfo(name = "lat")
        var lat: Double = 0.0,

        @ColumnInfo(name = "lon")
        var lon: Double = 0.0,
)
