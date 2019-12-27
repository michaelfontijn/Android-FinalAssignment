package com.example.macrobuddy.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.macrobuddy.Database.Converters.Converters
import com.example.macrobuddy.Database.DataAccessObjects.UserInformationDao
import com.example.macrobuddy.Database.DataAccessObjects.WeightMeasurementDao
import com.example.macrobuddy.Models.UserInformation
import com.example.macrobuddy.Models.WeightMeasurement

@Database(entities = [UserInformation::class, WeightMeasurement::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class MacroBuddyDatabase : RoomDatabase() {

    abstract fun userInforamtionDao(): UserInformationDao
    abstract fun weightMeasurementDao(): WeightMeasurementDao

    companion object {
        private const val DATABASE_NAME = "MACRO_BUDDY_DATABASE"

        @Volatile
        private var INSTANCE: MacroBuddyDatabase? = null

        fun getDatabase(context: Context): MacroBuddyDatabase? {
            if (INSTANCE == null) {
                synchronized(MacroBuddyDatabase::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context.applicationContext,
                            MacroBuddyDatabase::class.java, DATABASE_NAME
                        )
                            .fallbackToDestructiveMigration()
                            .build()
                    }
                }
            }
            return INSTANCE
        }
    }
}
