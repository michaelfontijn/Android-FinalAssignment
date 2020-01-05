package com.example.macrobuddy.ViewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.macrobuddy.Database.Repositories.WeightMeasurementRepository
import com.example.macrobuddy.Models.UserInformation
import com.example.macrobuddy.Models.WeightMeasurement
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class WeightHistoryAcivityViewModel(application: Application) : AndroidViewModel(application) {

    private val weightMeasurementRepository =
        WeightMeasurementRepository(application.applicationContext)

    var weightMeasurements: LiveData<List<WeightMeasurement>> = weightMeasurementRepository.getWeightMeasurements()
}