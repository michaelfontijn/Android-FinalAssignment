package com.example.macrobuddy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.macrobuddy.ViewModels.WeightHistoryAcivityViewModel

class WeightHistoryActivity : AppCompatActivity() {

    private lateinit var viewModel : WeightHistoryAcivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weight_historty)

        //init the view and viewModel
        initView()
    }

    /**
     * Init the view and its components
     */
    private fun initView(){

    }

    private fun initViewModel(){

    }
}
