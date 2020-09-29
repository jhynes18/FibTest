package com.hynes.james.fibtest.ui.summary

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.hynes.james.fibtest.R
import com.hynes.james.fibtest.model.FibonacciCalculationResult
import com.hynes.james.fibtest.util.ListItemViewHolder

class SummaryAdapter : RecyclerView.Adapter<ViewHolder>() {

    private var fibonacciCalculationResults = listOf<FibonacciCalculationResult>()

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {

        return ListItemViewHolder(LayoutInflater.from(viewGroup.context).inflate(R.layout.view_list_item, viewGroup, false))
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        (viewHolder as ListItemViewHolder).bind(
            fibonacciCalculationResults[position].inputNumber.toString(),
            fibonacciCalculationResults[position].calculationTime.toString()
        )
    }

    override fun getItemCount() = fibonacciCalculationResults.size

    fun updateFibonacciCalculationResults(fibonacciCalculationResults: List<FibonacciCalculationResult>) {

        this.fibonacciCalculationResults = fibonacciCalculationResults
        notifyDataSetChanged()
    }
}