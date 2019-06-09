package com.example.mydiagnosis.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.content.Context
import com.example.mydiagnosis.model.Condition
import com.example.mydiagnosis.usecase.DiagnoseUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DiagnosisResultViewModel : ViewModel() {

    var conditions : MutableLiveData<List<Condition>> = MutableLiveData()

    fun getDiagnosis(context: Context, userAge : Int?, userGender : String?) {

        CoroutineScope(Dispatchers.IO).launch {

            val useCase = DiagnoseUseCase(context, userAge, userGender)

            conditions.postValue(useCase.run())

        }

    }

}