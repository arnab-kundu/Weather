package com.mobiquity.arnab.weather.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mobiquity.arnab.weather.database.dao.CityDao
import com.mobiquity.arnab.weather.database.entity.CityEntity

/**
 * This is the main class that provides the database instance.
 * All Dao should be mentioned as an abstract function over here
 *
 * @param entities      All tables of database are mentioned in entities array.                 (Mandatory)
 * @param views         We don't have any views in this database, So its an empty array         (Optional)
 * @param version       Its maintain database version. Need to be updated on                    (Mandatory)
 *                      any table changes in final release. Needed migration rule as well
 * @param exportSchema  Its an optional parameter if you want to export database schema as      (Optional)
 *                      a json. By default its false.
 */
@Database(entities = [CityEntity::class], views = [], version = 1, exportSchema = true)
abstract class AppDatabase : RoomDatabase() {

    abstract fun cityDao(): CityDao

    companion object {

        @Volatile
        private var instance: AppDatabase? = null
        private var LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }


        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "Weather.db"
            ).build()
    }
}