package com.example.mydiagnosis.usecase

import android.content.Context
import android.widget.Toast
import com.example.mydiagnosis.database.DiagnosisRoomDatabase
import com.example.mydiagnosis.model.LabTest
import com.example.mydiagnosis.retrofit.RetrofitClient

class FetchLabTestsUseCase(val context: Context) {

    suspend fun run() : List<LabTest> {

        println("Fetching Lab Tests here")

        var labTests = emptyList<LabTest>()

        val database = DiagnosisRoomDatabase.getDatabase(context)

        val sharedPreferences = context.getSharedPreferences("shared", Context.MODE_PRIVATE)

        if(sharedPreferences.getBoolean("fetchedLabTests", false)) {

            // if data already fetched then get it from the database
            println("Fetching Lab Tests from database here")

            database?.let {

                return@run it.labTestsDao().getAllLabTests()
            }
        }

        // otherwise get data by making a call to web service api
        val labTestRequest = RetrofitClient.getApiService().getAvailableLabTestsAsync()

        val response = labTestRequest.await()

        println("Done awaiting for the response")

        if (response.isSuccessful) {
            println("Hey successfully got response")

            val labTestResponse = response.body()

            labTestResponse?.let {

                labTests = labTestResponse

            }

        } else {
            Toast.makeText(context, "Error fetching LabTests", Toast.LENGTH_LONG).show()
        }


        val editor = sharedPreferences.edit()
        editor.putBoolean("fetchedLabTests", true).apply()

        // store the information into the database if fetched first time

        database?.labTestsDao()?.insert(labTests)

        return labTests

    }
}