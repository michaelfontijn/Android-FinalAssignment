package com.example.macrobuddy.Models

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "weightMeasurementTable")
data class WeightMeasurement(
    @ColumnInfo(name = "weight")
    var weigth : Double,
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id : Long?
): Parcelable