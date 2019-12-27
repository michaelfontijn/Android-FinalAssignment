package com.example.macrobuddy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.macrobuddy.Enums.Gender
import com.example.macrobuddy.Enums.Goal
import com.example.macrobuddy.Enums.RateOfActivity
import com.example.macrobuddy.Models.UserInformation
import kotlinx.android.synthetic.main.activity_configuration.*
import java.util.*

class ConfigurationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_configuration)

        initView()
    }

    /**
     * Function to initialize the view and its components.
     */
    private fun initView() {
        datePicker.visibility = View.GONE
        btnToggleDate.setOnClickListener { toggleDatePicker() }
        btnCalculate.setOnClickListener { calculate() }
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
        //TODO implement
        val day = datePicker.dayOfMonth
        val month = datePicker.month + 1
        val year = datePicker.year
        var dateString = "$year/$month/$day"
        var temp = Date(dateString) //TODO use this on submit (just get txtDate text)
        txtDate.setText(dateString)
    }

    //TODO is this method required? maybe just use retrieve form input, the calculation is going to be performed on the next activity?
    private fun calculate() {
        retrieveFormInput()
    }

    private fun retrieveFormInput() {
        val goal: Int = getSelectedGoal()
        val gender: Int = getSelectedGender()
        val rateOfActivity: Int = getSelectedRateOfActivity()
        val length: Int = if(txtLength.text.toString().toIntOrNull() == null) -1 else txtLength.text.toString().toInt()
        val dateOfBirth: Date = Date(txtDate.text.toString())
        val currentWeight: Double = if(txtWeight.text.toString().toDoubleOrNull() == null) (-1).toDouble() else txtWeight.text.toString().toDouble()

        //TODO left of here, need to configure the view model and use it to create/ set the userInformation to the room database!
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
