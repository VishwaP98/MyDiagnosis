package com.example.mydiagnosis.database

import android.arch.persistence.room.TypeConverter
import com.example.mydiagnosis.model.LabTestResult
import com.example.mydiagnosis.model.MentionType
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {

    companion object {

        @JvmStatic
        @TypeConverter
        fun fromMentionTypeToString(mentionType: MentionType) : String {

            return mentionType.string

        }

        @JvmStatic
        @TypeConverter
        fun fromStringToMentionType(string: String) : MentionType {

            return MentionType.valueOf(string)
        }

        @JvmStatic
        @TypeConverter
        fun fromLabTestResult(labTestResult: LabTestResult) : String {

            return labTestResult.toString()
        }

        @JvmStatic
        @TypeConverter
        fun fromString(string: String) : List<LabTestResult> {
            val listType = object : TypeToken<List<LabTestResult>>() {

            }.type
            return Gson().fromJson(string, listType)
        }

        @JvmStatic
        @TypeConverter
        fun fromOptionsList(labTestResults : List<LabTestResult>) : String {

            return Gson().toJson(labTestResults)

        }

    }

}