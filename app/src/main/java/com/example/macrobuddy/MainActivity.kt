package com.example.macrobuddy

import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.math.log

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()
    }

    /**
     * Function to initialize the view and its components.
     */
    private fun initView() {
        datePicker.visibility = View.GONE
        btnToggleDate.setOnClickListener {toggleDatePicker()}
        btnCalculate.setOnClickListener{calculate()}
    }

    /**
     * Toggles the dateTime picker on and off based on its current state.
     */
    private fun toggleDatePicker() {
        if (datePicker.visibility == View.GONE) {
            datePicker.visibility = View.VISIBLE
            btnToggleDate.setImageDrawable(ContextCompat.getDrawable(this@MainActivity, android.R.drawable.ic_delete))
        } else {
            datePicker.visibility = View.GONE
            btnToggleDate.setImageDrawable(ContextCompat.getDrawable(this@MainActivity, android.R.drawable.ic_menu_my_calendar))
            retrievePickedDate()
        }
    }

    /**
     * Retrieves the picked date from the datePicker
     */
    private fun retrievePickedDate(){
        //TODO implement
        val day = datePicker.dayOfMonth
        val month = datePicker.month +1
        val year = datePicker.year
        var dateString = "$year/$month/$day"
        var temp = Date(dateString) //TODO use this on submit (just get txtDate text)
        txtDate.setText(dateString)
    }

    private fun calculate(){

    }

}
