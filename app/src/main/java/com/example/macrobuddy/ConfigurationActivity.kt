package com.example.macrobuddy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProviders
import com.example.macrobuddy.Database.Repositories.UserInformationRepository
import com.example.macrobuddy.Enums.Gender
import com.example.macrobuddy.Enums.Goal
import com.example.macrobuddy.Enums.RateOfActivity
import com.example.macrobuddy.Models.UserInformation
import com.example.macrobuddy.ViewModels.ConfigurationActivityViewModel
import kotlinx.android.synthetic.main.activity_configuration.*
import java.util.*

class ConfigurationActivity : AppCompatActivity() {

    private lateinit var viewModel : ConfigurationActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_configuration)

        initView()
        initViewModel()
    }

    /**
     * Function to initialize the view and its components.
     */
    private fun initView() {
        datePicker.visibility = View.GONE
        btnToggleDate.setOnClickListener { toggleDatePicker() }
        btnSaveConfig.setOnClickListener { saveConfig() }
    }

    /**
     * Initialize/ configure the viewModel
     */
    private fun initViewModel() {
        //initialize the viewModel with an empty game object
        viewModel = ViewModelProviders.of(this).get(ConfigurationActivityViewModel::class.java)

        viewModel.error.observe(this , androidx.lifecycle.Observer { message ->
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        })

        //observe the success property of the vm so we know when the action has been completed succesfully
        viewModel.success.observe(this, androidx.lifecycle.Observer { success ->
            //when the configuration was successfully stored go to the home activity
            if (success) startActivity(Intent(this, HomeActivity::class.java))
        })

        viewModel.userInformationObj.observe(this, androidx.lifecycle.Observer { obj ->
            //if there is already a userInformationRecord in the database continue to homeActivity right away
            if(obj != null) startActivity(Intent(this, HomeActivity::class.java))
        })
    }

    /**
     * Toggles the dateTime picker on and off based on its current state.
     */
    private fun toggleDatePicker() {
        if (datePicker.visibility == View.GONE) {
            datePicker.visibility = View.VISIBLE
            btnToggleDate.setImageDrawable(
                ContextCompat.getDrawable(
                    this@ConfigurationActivity,
                    android.R.drawable.ic_delete
                )
            )
        } else {
            datePicker.visibility = View.GONE
            btnToggleDate.setImageDrawable(
                ContextCompat.getDrawable(
                    this@ConfigurationActivity,
                    android.R.drawable.ic_menu_my_calendar
                )
            )
            retrievePickedDate()
        }
    }

    /**
     * Retrieves the picked date from the datePicker
     */
    private fun retrievePickedDate() {
        val day = datePicker.dayOfMonth
        val month = datePicker.month + 1
        val year = datePicker.year
        var dateString = "$year/$month/$day"
        txtDate.setText(dateString)
    }

    /**
     * Saves the userInformation config to the database
     */
    private fun saveConfig() {
        var userInfo = retrieveFormInput()
        viewModel.userInformation.value =  userInfo
        viewModel.setUserInformation()
    }

    /**
     * Retrieve the form input and serialize it to a UserInformation object
     */
    private fun retrieveFormInput() : UserInformation  {
        val goal: Int = getSelectedGoal()
        val gender: Int = getSelectedGender()
        val rateOfActivity: Int = getSelectedRateOfActivity()
        val length: Int = if(txtLength.text.toString().toIntOrNull() == null) -1 else txtLength.text.toString().toInt()
        val dateOfBirth: Date = Date(txtDate.text.toString())
        val currentWeight: Double = if(txtWeight.text.toString().toDoubleOrNull() == null) (-1).toDouble() else txtWeight.text.toString().toDouble()

        return UserInformation(goal,gender,rateOfActivity,length,dateOfBirth,currentWeight)
       }

    /**
     * Retrieves the value of the selected gender in the radioButtonGroup and maps the value to its
     * associated enum value
     */
    private fun getSelectedGender(): Int {
        //get the id of the selected radioButton
        var selectedGenderId: Int = radioSex.checkedRadioButtonId

        return when (resources.getResourceEntryName(selectedGenderId)) {
            resources.getResourceEntryName(R.id.radioFemale) -> Gender.FEMALE.ordinal
            resources.getResourceEntryName(R.id.radioMale) -> Gender.MALE.ordinal
            else -> -1
        }
    }

    /**
     * Retrieves the value of the selected goal in the radioButtonGroup and maps the value to its
     * associated enum value
     */
    private fun getSelectedGoal(): Int {
        //get the id of the selected radioButton
        var selectedGoalId: Int = radioGoal.checkedRadioButtonId

        return when (resources.getResourceEntryName(selectedGoalId)) {
            resources.getResourceEntryName(R.id.radioGoalGain) -> Goal.WEIGHT_GAIN.ordinal
            resources.getResourceEntryName(R.id.radioGoalLose) -> Goal.WEIGHT_LOSS.ordinal
            resources.getResourceEntryName(R.id.radioGoalMaintain) -> Goal.MAINTAINANCE.ordinal
            else -> -1
        }
    }

    /**
     * Retrieves the value of the selected rateOfActivity in the radioButtonGroup and maps the value to its
     * associated enum value
     */
    private fun getSelectedRateOfActivity() : Int{
        //get the id of the selected radioButton
        var selectedRateOfActivityId: Int = radioRateOfActivity.checkedRadioButtonId

        return when (resources.getResourceEntryName(selectedRateOfActivityId)) {
            resources.getResourceEntryName(R.id.radioActivityOne) -> RateOfActivity.NOT_ACTIVE.ordinal
            resources.getResourceEntryName(R.id.radioActivityTwo) -> RateOfActivity.LIGHTLY_ACTIVE.ordinal
            resources.getResourceEntryName(R.id.radioActivityThree) -> RateOfActivity.MODERATELEY_ACTIVE.ordinal
            resources.getResourceEntryName(R.id.radioActivityFour) -> RateOfActivity.VERY_ACTIVE.ordinal
            resources.getResourceEntryName(R.id.radioActivityFive) -> RateOfActivity.EXTREMELY_ACTIVE.ordinal
            else -> -1
        }
    }

}
