package com.example.macrobuddy.Database.DataAccessObjects

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.macrobuddy.Models.UserInformation


@Dao
interface UserInformationDao{
    @Insert
    fun setUserInformation(userInformation: UserInformation)

    @Query("SELECT * FROM userInformationTable")
    fun getUserInformation(): LiveData<UserInformation>
}