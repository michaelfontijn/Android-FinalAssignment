package com.example.macrobuddy.ViewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.macrobuddy.Database.Repositories.UserInformationRepository
import com.example.macrobuddy.Models.UserInformation
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

class HomeAcitivityViewModel(application: Application) : AndroidViewModel(application) {

    private val userInformationRepository =
        UserInformationRepository(application.applicationContext)
    private val mainScope = CoroutineScope(Dispatchers.Main)

    val userInformation : LiveData<UserInformation> = userInformationRepository.getUserInformation()
    val error = MutableLiveData<String?>()
    val success = MutableLiveData<Boolean>()

}