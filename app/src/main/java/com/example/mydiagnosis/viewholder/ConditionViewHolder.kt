package com.example.mydiagnosis.viewholder

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.example.mydiagnosis.R

class ConditionViewHolder(item : View) : RecyclerView.ViewHolder(item) {

    private val conditionName = item.findViewById<TextView>(R.id.condition_txt_title)

    private val conditionProbability = item.findViewById<TextView>(R.id.condition_probability)

    fun getConditionName() : TextView {
        return conditionName
    }

    fun getConditionProbability() : TextView {
        return conditionProbability
    }

}