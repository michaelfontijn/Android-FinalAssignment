package com.example.macrobuddy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.macrobuddy.Adapters.WeightMeasurementAdapter
import com.example.macrobuddy.Models.WeightMeasurement
import com.example.macrobuddy.ViewModels.WeightHistoryAcivityViewModel
import kotlinx.android.synthetic.main.activity_weight_historty.*
import androidx.recyclerview.widget.DividerItemDecoration


class WeightHistoryActivity : AppCompatActivity() {

    private lateinit var viewModel: WeightHistoryAcivityViewModel

    //adapter variables
    private val measurements = arrayListOf<WeightMeasurement>()
    private val weightMeasurementAdapter = WeightMeasurementAdapter(measurements)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weight_historty)

        initViewModel()
        initRecyclerView()
    }


    /**
     * Initialize the view model
     */
    private fun initViewModel() {
        //initialize the view model
        viewModel = ViewModelProviders.of(this).get(WeightHistoryAcivityViewModel::class.java)

        viewModel.weightMeasurements.observe(this, Observer { measurements ->
            this.measurements.clear()
            this.measurements.addAll(measurements.toCollection(ArrayList()))
            weightMeasurementAdapter.notifyDataSetChanged()
        })
    }

    /**
     * Initialize/ configure the adapter and the recyclerView
     */
    private fun initRecyclerView() {
        //Bind the adapter and layout manger to the recyclerView.
        rvHistory.layoutManager =
            LinearLayoutManager(this@WeightHistoryActivity, RecyclerView.VERTICAL, false)
        //add a horizontal divider (line/ border) at the bottom of the items in the collection
        rvHistory.addItemDecoration(
            DividerItemDecoration(
                this@WeightHistoryActivity,
                DividerItemDecoration.VERTICAL
            )
        )
        rvHistory.adapter = weightMeasurementAdapter
    }
}
