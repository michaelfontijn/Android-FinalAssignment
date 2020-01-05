package com.example.macrobuddy.Database.Repositories

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.macrobuddy.Database.DataAccessObjects.UserInformationDao
import com.example.macrobuddy.Database.MacroBuddyDatabase
import com.example.macrobuddy.Models.UserInformation

class UserInformationRepository(context: Context) {

    private val userInformationDao : UserInformationDao
    init {
        val database = MacroBuddyDatabase.getDatabase(context)
        userInformationDao = database!!.userInforamtionDao()
    }

    /**
     * Retrieves the current user information / config
     */
    fun getUserInformation(): LiveData<UserInformation> {
        return userInformationDao.getUserInformation()
    }

    /**
     * Updates the current weigth of the user and logs the weights as history
     */
    fun updateCurrentWeight(newWeight : Double){
        userInformationDao.updateCurrentWeigth(newWeight)
      }

    /**
     * Inserts a new record if none is present yet otherwise updates the existing record.
     */
    fun setUserInformation(userInformation : UserInformation){
        var obj = userInformationDao.getUserInfromationObj()

        //in case no user information record is present yet insert it, otherwise update the existing record
        if(obj == null){
            userInformationDao.insertUserInformation(userInformation)
        }else{
            obj.apply {
                goal = userInformation.goal
                gender = userInformation.gender
                rateOfActivity = userInformation.rateOfActivity
                length = userInformation.length
                dateOfBirth = userInformation.dateOfBirth
                currentWeight = userInformation.currentWeight
            }
            userInformationDao.updateUserInformation(obj)
        }
    }
}