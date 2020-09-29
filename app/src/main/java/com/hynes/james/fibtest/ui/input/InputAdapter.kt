package com.hynes.james.fibtest.ui.input

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.hynes.james.fibtest.R
import com.hynes.james.fibtest.util.ListItemViewHolder

class InputAdapter : RecyclerView.Adapter<ViewHolder>() {

    private var fibonacciResults = listOf<Long>()

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {

        return ListItemViewHolder(LayoutInflater.from(viewGroup.context).inflate(R.layout.view_list_item, viewGroup, false))
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        (viewHolder as ListItemViewHolder).bind(position.toString(), fibonacciResults[position].toString())
    }

    override fun getItemCount() = fibonacciResults.size

    fun updateFibonacciResults(fibonacciResults: List<Long>) {

        this.fibonacciResults = fibonacciResults
        notifyDataSetChanged()
    }
}