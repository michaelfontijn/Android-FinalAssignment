package com.example.macrobuddy.Database.DataAccessObjects

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.macrobuddy.Models.UserInformation


@Dao
interface UserInformationDao{
    @Insert
    fun insertUserInformation(userInformation: UserInformation)

    @Query("SELECT * FROM userInformationTable LIMIT 1")
    fun getUserInformation(): LiveData<UserInformation>

    @Query("SELECT * FROM userInformationTable LIMIT 1")
    fun getUserInfromationObj() :UserInformation

    @Update
    fun updateUserInformation(userInformation: UserInformation)

    @Query("UPDATE userinformationtable SET currentWeight=:newWeight WHERE ID =(SELECT ID FROM userinformationtable LIMIT 1) ")
    fun updateCurrentWeigth(newWeight : Double)



}