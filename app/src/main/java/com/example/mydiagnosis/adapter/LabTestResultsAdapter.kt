package com.example.mydiagnosis.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.mydiagnosis.R
import com.example.mydiagnosis.model.LabTestResult
import com.example.mydiagnosis.viewholder.LabTestResultViewHolder
import com.example.mydiagnosis.viewmodel.LabTestsViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LabTestResultsAdapter(val context: Context, val labTestResults : List<LabTestResult>, val layoutInflater: LayoutInflater,
                      val labTestsViewModel: LabTestsViewModel) : RecyclerView.Adapter<LabTestResultViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): LabTestResultViewHolder {
        val view = layoutInflater.inflate(R.layout.lab_test_rec_layout, p0, false)

        return LabTestResultViewHolder(view)
    }

    override fun getItemCount(): Int {
        return labTestResults.size
    }

    override fun onBindViewHolder(p0: LabTestResultViewHolder, p1: Int) {

        p0.getRadioButton().text = labTestResults[p1].getOption()

        p0.getRadioButton().setOnClickListener {

            CoroutineScope(Dispatchers.IO).launch {

                labTestsViewModel.onLabTestResultAdded(context, labTestResults[p1].getId())
            }

        }

    }


}
