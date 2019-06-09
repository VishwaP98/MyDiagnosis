package com.example.mydiagnosis.viewholder

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.ToggleButton
import com.example.mydiagnosis.R

class RiskFactorViewHolder(item : View) : RecyclerView.ViewHolder(item) {

    private val riskFactorName = item.findViewById<TextView>(R.id.risk_factor_txt_title)
    private val riskFactorGender = item.findViewById<TextView>(R.id.risk_factor_txt_message)
    private val riskFactorSeverityIcon = item.findViewById<ImageView>(R.id.severity_icon_risk_factor)
    private val riskFactorPresentToggle = item.findViewById<ToggleButton>(R.id.risk_factor_toggleButton)

    fun getRiskFactorName() : TextView {
        return riskFactorName
    }

    fun getRiskFactorGender() : TextView {
        return riskFactorGender
    }

    fun getRiskFactorSeverityIcon() : ImageView {
        return riskFactorSeverityIcon
    }

    fun getRiskFactorToggleButton() : ToggleButton {
        return riskFactorPresentToggle
    }

}