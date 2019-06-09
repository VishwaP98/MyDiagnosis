package com.example.mydiagnosis.viewholder

import android.media.Image
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.ToggleButton
import com.example.mydiagnosis.R

class SymptomViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

    private val symptomName = view.findViewById<TextView>(R.id.item_txt_title)
    private val symptomGender = view.findViewById<TextView>(R.id.item_txt_message)
    private val symptomSeverityIcon = view.findViewById<ImageView>(R.id.severity_icon)
    private val symptomToggleButton = view.findViewById<ToggleButton>(R.id.toggleButton)

    fun getSymptomName() : TextView {
        return symptomName
    }

    fun getSymptomGender() : TextView {
        return symptomGender
    }

    fun getSymptonSeverityIcon() : ImageView {
        return symptomSeverityIcon
    }

    fun getSymptomToggleButton() : ToggleButton {
        return symptomToggleButton
    }

}