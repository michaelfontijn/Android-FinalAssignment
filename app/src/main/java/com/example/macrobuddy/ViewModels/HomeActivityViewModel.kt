package com.example.macrobuddy.ViewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.macrobuddy.Database.Repositories.UserInformationRepository
import com.example.macrobuddy.Database.Repositories.WeightMeasurementRepository
import com.example.macrobuddy.Models.UserInformation
import com.example.macrobuddy.Models.WeightMeasurement
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*

class HomeActivityViewModel(application: Application) : AndroidViewModel(application) {

    private val userInformationRepository =
        UserInformationRepository(application.applicationContext)
    private val weightMeasurementRepository =
        WeightMeasurementRepository(application.applicationContext)

    private val mainScope = CoroutineScope(Dispatchers.Main)

    var userInformation: LiveData<UserInformation> = userInformationRepository.getUserInformation()
    val error = MutableLiveData<String?>()
    val successMsg = MutableLiveData<String>()

    /**
     * Updates the weight of the user and logs the history
     */
    fun saveNewWeight(newWeight: Double) {
        mainScope.launch {
            withContext(Dispatchers.IO) {
                //update the weight
                userInformationRepository.updateCurrentWeight(newWeight)
                //log the weight change
                weightMeasurementRepository.insertWeightMeasurement(WeightMeasurement(newWeight, Calendar.getInstance().time))
            }
            successMsg.value = "Gewicht successvol bijgewerkt"
        }
    }

    /**
     * Refresh the user information of this vm using the most recent data from the database
     */
    fun refreshUserInformation() {
        mainScope.launch {
            withContext(Dispatchers.IO) {
                userInformation.value.apply { userInformationRepository.getUserInformation().value }
            }
        }
    }
}