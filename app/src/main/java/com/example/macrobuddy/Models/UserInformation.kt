package com.example.macrobuddy.Models

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
@Entity(tableName = "userInformationTable")
data class UserInformation(
    @ColumnInfo(name = "goal")
    var goal : Int,
    @ColumnInfo(name = "gender")
    var gender: Int,
    @ColumnInfo(name = "dateOfBirth")
    var dateOfBirth : Date? = null,
    @ColumnInfo(name = "length")
    var length : Int,
    @ColumnInfo(name = "rateOfActivity")
    var rateOfActivity : Int,
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id : Long? = null
): Parcelable