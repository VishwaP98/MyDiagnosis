package com.example.mydiagnosis.usecase

import android.content.Context
import com.example.mydiagnosis.database.DiagnosisRoomDatabase
import com.example.mydiagnosis.model.Condition
import com.example.mydiagnosis.retrofit.RetrofitClient
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONArray

class DiagnoseUseCase(val context: Context, val userAge : Int?, val userGender : String?) {

    suspend fun run() : List<Condition> {

        // we want to get all data from evidence table and add it to a Json object where each
        // entry in the JSON object is a JSON object

        // for now let's set the age and sex of the user

        var conditions = listOf<Condition>()

        val requestJsonObject = JsonObject()

        var evidencesJsonObject = JsonArray()

        val database = DiagnosisRoomDatabase.getDatabase(context)

        database?.let {

            val evidences = it.evidencesDao().getAllEvidences()

            for (item in evidences) {

                val jsonObject = JsonObject()

                jsonObject.addProperty("id", item.getId())
                jsonObject.addProperty("choice_id", item.getChoiceId())

                evidencesJsonObject.add(jsonObject)

            }

        }

        userAge?.let {
            requestJsonObject.addProperty("age", userAge)
        }


        userGender?.let {
            requestJsonObject.addProperty("sex", userGender)

        }

        requestJsonObject.add("evidence", evidencesJsonObject)


        val request = RetrofitClient.getApiService().diagnose(requestJsonObject)

        val conditionsResponse = request.await().body()

        conditionsResponse?.let {

            database?.let {

                database.conditionsDao().deleteAll()

                database.conditionsDao().insertConditions(conditionsResponse.getConditions())

            }

            conditions = conditionsResponse.getConditions()
        }

        return conditions
    }

}