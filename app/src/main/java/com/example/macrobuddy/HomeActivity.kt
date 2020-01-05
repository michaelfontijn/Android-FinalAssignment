package com.example.macrobuddy

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.macrobuddy.Managers.CalculationManager
import com.example.macrobuddy.Models.CalculationResult
import com.example.macrobuddy.ViewModels.HomeActivityViewModel
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    private lateinit var viewModel: HomeActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        initView()
        initViewModel()
    }

    /**
     * Init the view and its components
     */
    private fun initView() {
        btnSaveWeight.setOnClickListener {
            saveWeight()
            txtCurrentWeigth.hideKeyboard()
        }
        btnHistory.setOnClickListener{
            startActivity(Intent(this, WeightHistoryActivity::class.java))
        }
    }

    /**
     * Initialize/ configure the viewModel
     */
    private fun initViewModel() {
        //initialize the view model
        viewModel = ViewModelProviders.of(this).get(HomeActivityViewModel::class.java)

        viewModel.userInformation.observe(this, Observer { obj ->
            txtCurrentWeigth.setText(obj.currentWeight.toString())

            //perform the calculation and show the results
            var calculationResult = CalculationManager.performCalculation(obj)
            showCalculationResults(calculationResult)
        })

        //if an error is thrown in the view model show it to the user
        viewModel.error.observe(this, Observer { message ->
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        })

        //observe the success property of the vm so we know when the action has been completed successfully
        viewModel.successMsg.observe(this, Observer { msg ->
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
            //make the viewModel retrieve the updated userInformation which will trigger the re-calculation of the macro's
            viewModel.refreshUserInformation()
        })
    }

    /**
     * Save the new weight to the userInfo in the databse
     */
    private fun saveWeight() {
        var newWeight = txtCurrentWeigth.text.toString().toDoubleOrNull()

        if (newWeight != null) {
            viewModel.saveNewWeight(newWeight)
        }
    }

    /**
     * extension method on View to hide the keyboard. example et1.hideKeyboard()
     */
    private fun View.hideKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }

    /**
     * Prints the calculation results to the activity
     */
    private fun showCalculationResults(calculationResult: CalculationResult) {
        tvCallories.text = calculationResult.calories.toString()
        tvProtein.text = calculationResult.protein.toString()
        tvCarbs.text = calculationResult.carbs.toString()
        tvVats.text = calculationResult.vats.toString()
    }
}
