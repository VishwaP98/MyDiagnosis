package com.example.mydiagnosis.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.content.Context
import com.example.mydiagnosis.model.Symptom
import com.example.mydiagnosis.usecase.FetchSymptomsUseCase
import com.example.mydiagnosis.usecase.SelectEvidenceUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SymptomViewModel : ViewModel() {

    var symptomsFound : MutableLiveData<List<Symptom>> = MutableLiveData()

    fun fetchSymptoms(context: Context) {

        CoroutineScope(Dispatchers.IO).launch {

            val fetchSymptomsUseCaseResult = FetchSymptomsUseCase(context).run()

            symptomsFound.postValue(fetchSymptomsUseCaseResult)

        }

    }

    suspend fun onAddedSymptomEvidence(context: Context, symptomId : String, symptomChoice : String) {

        SelectEvidenceUseCase().run(context, symptomId, symptomChoice)

    }

}