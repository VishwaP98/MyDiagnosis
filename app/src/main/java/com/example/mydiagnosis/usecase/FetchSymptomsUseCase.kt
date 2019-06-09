package com.example.mydiagnosis.usecase

import android.arch.lifecycle.MutableLiveData
import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import android.widget.Toast
import com.example.mydiagnosis.database.DiagnosisRoomDatabase
import com.example.mydiagnosis.model.Symptom
import com.example.mydiagnosis.retrofit.RetrofitClient

class FetchSymptomsUseCase(val context: Context) {

    suspend fun run() : List<Symptom> {

        println("Calling Symptoms here")

        var symptomsRetrieved = emptyList<Symptom>()

        val database = DiagnosisRoomDatabase.getDatabase(context)


        val sharedPreferences = context.getSharedPreferences("shared", Context.MODE_PRIVATE)

        if(sharedPreferences.getBoolean("fetchedSymptoms", false)) {

            // if data already fetched then get it from the database
            println("Fetching symptoms from database here")

            database?.let {

                return@run it.symptomsDao().getAllSymptoms()
            }
        }

        // otherwise get data by making a call to web service api
        val symptomsRequest = RetrofitClient.getApiService().getSymptomsAsync()

        val response = symptomsRequest.await()

        println("Done awaiting for the response")

        if (response.isSuccessful) {
            println("Hey successfully got response")

            val symptomsList = response.body()

            symptomsList?.let {

                symptomsRetrieved = symptomsList

            }

        } else {
            Toast.makeText(context, "Error fetching Symptoms", Toast.LENGTH_LONG).show()
        }


        val editor = sharedPreferences.edit()
        editor.putBoolean("fetchedSymptoms", true).apply()

        // store the information into the database if fetched first time

        database?.symptomsDao()?.insert(symptomsRetrieved)

        return symptomsRetrieved
    }

}
