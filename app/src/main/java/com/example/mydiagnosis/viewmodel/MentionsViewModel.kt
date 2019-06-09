package com.example.mydiagnosis.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.content.Context
import com.example.mydiagnosis.model.Mention
import com.example.mydiagnosis.usecase.ParseSymptomsUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MentionsViewModel : ViewModel() {

    var mentions: MutableLiveData<List<Mention>> = MutableLiveData()

    fun getSymptomMentions(context: Context, userMessage : String) {

        CoroutineScope(Dispatchers.IO).launch {

            val parseSymptomsUseCase = ParseSymptomsUseCase(context)

            mentions.postValue(parseSymptomsUseCase.run(userMessage))

        }

    }

}