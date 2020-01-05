package com.example.macrobuddy.ViewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.macrobuddy.Database.Repositories.UserInformationRepository
import com.example.macrobuddy.Models.UserInformation
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ConfigurationActivityViewModel(application: Application) : AndroidViewModel(application) {

    private val userInformationRepository = UserInformationRepository(application.applicationContext)
    private val mainScope = CoroutineScope(Dispatchers.Main)

    val userInformation = MutableLiveData<UserInformation>()
    val userInformationObj : LiveData<UserInformation> = userInformationRepository.getUserInformation()
    val error = MutableLiveData<String?>()
    val success = MutableLiveData<Boolean>()


    /**
     * Store the input from the user from the config form in the database
     */
    fun setUserInformation() {
        if (isUserInputValid()) {
            mainScope.launch {
                withContext(Dispatchers.IO) {
                    userInformationRepository.setUserInformation(userInformation.value!!)
                }
                success.value = true
            }
        }
    }

    /**
     * Basic validation method
     */
    private fun isUserInputValid(): Boolean {
        return when {
            userInformation.value == null -> {
                error.value = "Gebruikers informatie dient te worden aangeleverd."
                false
            }
            userInformation.value!!.length == -1 -> {
                error.value = "Lengte dient te worden ingevuld"
                false
            }
            userInformation.value!!.goal == -1 -> {
                error.value = "Specificeer een doel en probeer het opnieuw"
                false
            }
            userInformation.value!!.gender == -1 -> {
                error.value = "U dient een geslacht te selecteren"
                false
            }
            userInformation.value!!.dateOfBirth == null -> {
                error.value = "De ingevulde waarde voor geboorte datum is incorrect, probeer het opnieuw"
                false
            } userInformation.value!!.rateOfActivity == -1 -> {
                error.value = "U dient een activiteitsgraad te kiezen"
                false
            }
            userInformation.value!!.currentWeight == (-1).toDouble() -> {
                error.value = "Het opgegeven huidige gewicht is incorrect, probeer het opnieuw"
                false
            }

            else -> true
        }
    }

}