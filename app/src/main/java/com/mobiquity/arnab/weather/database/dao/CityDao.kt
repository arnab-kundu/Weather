package com.mobiquity.arnab.weather.database.dao

import androidx.room.*
import com.mobiquity.arnab.weather.database.entity.CityEntity

/**
 * All the required database quires related to `City` table is declared in this Interface
 */
@Dao
interface CityDao {
    @get:Query("SELECT * FROM city")
    val all: List<CityEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg city: CityEntity)

    @Delete
    suspend fun delete(city: CityEntity)

    @get:Query("Select count(*) from city;")
    val count: Long

    @Query("Delete from city where 1 = 1")
    suspend fun truncateTable()

    @Query("SELECT * FROM city where name = :searchString")
    fun searchCity(searchString: String): List<CityEntity>

}