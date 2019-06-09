package com.example.mydiagnosis.usecase

import android.content.Context
import android.widget.Toast
import com.example.mydiagnosis.database.DiagnosisRoomDatabase
import com.example.mydiagnosis.model.RiskFactor
import com.example.mydiagnosis.retrofit.RetrofitClient

class FetchRiskFactorsUseCase(val context: Context) {

    suspend fun run() : List<RiskFactor> {

        println("Fetching Lab Tests here")

        var riskFactors = emptyList<RiskFactor>()

        val database = DiagnosisRoomDatabase.getDatabase(context)

        val sharedPreferences = context.getSharedPreferences("shared", Context.MODE_PRIVATE)

        if(sharedPreferences.getBoolean("fetchedRiskFactors", false)) {

            // if data already fetched then get it from the database
            println("Fetching Risk Factors from database here")

            database?.let {

                return@run it.riskFactorsDao().getAllRiskFactors()
            }
        }

        // otherwise get data by making a call to web service api
        val riskFactorsRequest = RetrofitClient.getApiService().getRiskFactorsAsync()

        val response = riskFactorsRequest.await()

        println("Done awaiting for the response")

        if (response.isSuccessful) {
            println("Hey successfully got response")

            val riskFactorsResponse = response.body()

            riskFactorsResponse?.let {

                riskFactors = riskFactorsResponse

            }

        } else {
            Toast.makeText(context, "Error fetching RiskFactors", Toast.LENGTH_LONG).show()
        }


        val editor = sharedPreferences.edit()
        editor.putBoolean("fetchedRiskFactors", true).apply()

        // store the information into the database if fetched first time

        database?.riskFactorsDao()?.insert(riskFactors)

        return riskFactors

    }

}