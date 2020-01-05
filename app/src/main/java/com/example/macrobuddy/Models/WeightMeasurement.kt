package com.example.macrobuddy.Models

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
@Entity(tableName = "weightMeasurementTable")
data class WeightMeasurement(
    @ColumnInfo(name = "weight")
    var weight : Double,
    @ColumnInfo(name = "date")
    var date : Date,
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id : Long? = null
): Parcelable