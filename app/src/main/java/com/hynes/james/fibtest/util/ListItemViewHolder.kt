package com.hynes.james.fibtest.util

import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.hynes.james.fibtest.databinding.ViewListItemBinding

class ListItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val binding: ViewListItemBinding? = DataBindingUtil.bind(itemView)

    fun bind(text1: String, text2: String) {

        binding?.textView1?.text = text1
        binding?.textView2?.text = text2
    }
}