package com.example.mydiagnosis.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.content.Context
import com.example.mydiagnosis.model.LabTest
import com.example.mydiagnosis.usecase.FetchLabTestsUseCase
import com.example.mydiagnosis.usecase.SelectEvidenceUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LabTestsViewModel : ViewModel() {

    var labTests : MutableLiveData<List<LabTest>> = MutableLiveData()

    fun fetchLabTests(context: Context) {

        CoroutineScope(Dispatchers.IO).launch {

            val useCase = FetchLabTestsUseCase(context)

            labTests.postValue(useCase.run())

        }

    }

    suspend fun onLabTestResultAdded(context: Context, labTestResultId : String) {

        SelectEvidenceUseCase().run(context, labTestResultId, "present")

    }



}