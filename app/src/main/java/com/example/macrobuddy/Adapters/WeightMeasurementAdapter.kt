package com.example.macrobuddy.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.macrobuddy.Models.WeightMeasurement
import com.example.macrobuddy.R
import kotlinx.android.synthetic.main.weight_history_item.view.*

class WeightMeasurementAdapter(private val measurements: List<WeightMeasurement>) :
    RecyclerView.Adapter<WeightMeasurementAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater
                .from(parent.context).inflate(R.layout.weight_history_item, parent, false)
        )
    }

    /**
     * Returns the size of the list
     */
    override fun getItemCount(): Int {
        return measurements.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(measurements[position])
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(measurement: WeightMeasurement) {
            itemView.tvWeight.text = measurement.weight.toString()
            itemView.tvDate.text = measurement.date.toString()
        }
    }


}