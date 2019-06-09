package com.example.mydiagnosis.fragment

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.annotation.Nullable
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mydiagnosis.R
import com.example.mydiagnosis.adapter.RiskFactorsAdapter
import com.example.mydiagnosis.model.RiskFactor
import com.example.mydiagnosis.viewmodel.RiskFactorsViewModel

class ThirdFragment : Fragment() {

    private lateinit var riskFactorsViewModel: RiskFactorsViewModel
    private lateinit var recyclerView: RecyclerView

    private fun onRiskFactorsFetched(riskFactors: List<RiskFactor>, recyclerView: RecyclerView) {

        recyclerView.adapter = RiskFactorsAdapter(requireContext(), riskFactors, this.layoutInflater, riskFactorsViewModel)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

    }

    override fun onCreate(@Nullable savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        riskFactorsViewModel = ViewModelProviders.of(this).get(RiskFactorsViewModel::class.java)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.third_fragment, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        println("Fetching risk factors")

        recyclerView = view.findViewById(R.id.risk_factors_recyclerview)

        riskFactorsViewModel.riskFactors.observe(this, Observer {

            it?.let{ onRiskFactorsFetched(it, recyclerView) }

        })

        val localContext : Context? = activity

        if(localContext == null) {
            return
        }

        riskFactorsViewModel.fetchRiskFactors(localContext)

    }



}