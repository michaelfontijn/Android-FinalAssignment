package com.example.macrobuddy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.macrobuddy.Models.UserInformation
import com.example.macrobuddy.ViewModels.ConfigurationActivityViewModel
import com.example.macrobuddy.ViewModels.HomeAcitivityViewModel

class HomeActivity : AppCompatActivity() {

    private lateinit var viewModel : HomeAcitivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
    }


    private fun initView(){

    }

    /**
     * Initialize/ configure the viewModel
     */
    private fun initViewModel() {
        //initialize the viewModel with an empty game object
        viewModel = ViewModelProviders.of(this).get(HomeAcitivityViewModel::class.java)

        viewModel.userInformation.observe(this, androidx.lifecycle.Observer { obj ->
            soms(obj)
        })

        //if an error is thrown in the view model show it to the user
        viewModel.error.observe(this , androidx.lifecycle.Observer { message ->
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        })

        //observe the success property of the vm so we know when the action has been completed succesfully
        viewModel.success.observe(this, androidx.lifecycle.Observer { success ->
            //when the configuration was successfully stored go to the home activity
            if (success) Toast.makeText(this, "Done", Toast.LENGTH_SHORT).show()
        })
    }



}
