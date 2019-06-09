package com.example.mydiagnosis.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.content.Context
import com.example.mydiagnosis.model.RiskFactor
import com.example.mydiagnosis.usecase.FetchRiskFactorsUseCase
import com.example.mydiagnosis.usecase.SelectEvidenceUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RiskFactorsViewModel : ViewModel() {

    var riskFactors : MutableLiveData<List<RiskFactor>> = MutableLiveData()

    fun fetchRiskFactors(context: Context) {

        CoroutineScope(Dispatchers.IO).launch {

            val fetchRiskFactorsUseCase = FetchRiskFactorsUseCase(context)

            riskFactors.postValue(fetchRiskFactorsUseCase.run())

        }

    }

    suspend fun onRiskFactorAdded(context: Context, riskFactorId : String, riskFactorPresent : String) {

        SelectEvidenceUseCase().run(context, riskFactorId, riskFactorPresent)

    }


}