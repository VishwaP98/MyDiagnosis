package com.example.mydiagnosis.viewholder

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.RadioButton
import com.example.mydiagnosis.R

class LabTestResultViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

    private val radioButton = view.findViewById<RadioButton>(R.id.labtest_checkbox)

    fun getRadioButton() : RadioButton {

        return radioButton
    }

}