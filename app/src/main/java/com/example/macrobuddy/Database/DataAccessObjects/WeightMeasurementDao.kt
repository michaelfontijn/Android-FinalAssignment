package com.example.macrobuddy.Database.DataAccessObjects

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.macrobuddy.Models.WeightMeasurement


@Dao
interface WeightMeasurementDao{
    @Insert
    fun insertWeightMeasurement(weightMeasurement: WeightMeasurement)

    @Query("SELECT * FROM weightMeasurementTable")
    fun getMeasurements(): LiveData<List<WeightMeasurement>>
}