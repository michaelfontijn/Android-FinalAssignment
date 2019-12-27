package com.example.macrobuddy.Database.Repositories

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.macrobuddy.Database.DataAccessObjects.UserInformationDao
import com.example.macrobuddy.Database.DataAccessObjects.WeightMeasurementDao
import com.example.macrobuddy.Database.MacroBuddyDatabase
import com.example.macrobuddy.Models.UserInformation
import com.example.macrobuddy.Models.WeightMeasurement

class WeightMeasurementRepository(context: Context) {

    private val weightMeasurementDao : WeightMeasurementDao
    init {
        val database = MacroBuddyDatabase.getDatabase(context)
        weightMeasurementDao = database!!.weightMeasurementDao()
    }

    fun getWeightMeasurements(): LiveData<List<WeightMeasurement>> {
        return weightMeasurementDao.getMeasurements()
    }

    fun insertWeightMeasurement(weightMeasurement : WeightMeasurement){
        weightMeasurementDao.insertWeightMeasurement(weightMeasurement)
    }
}