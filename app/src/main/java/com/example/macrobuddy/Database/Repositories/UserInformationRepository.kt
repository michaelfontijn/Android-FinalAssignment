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

    fun getUserInformation(): LiveData<UserInformation> {
        return userInformationDao.getUserInformation()
    }

    fun setUserInformation(userInformation : UserInformation){
        userInformationDao.setUserInformation(userInformation)
    }
}