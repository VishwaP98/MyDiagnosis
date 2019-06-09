package com.example.mydiagnosis.database

import android.arch.persistence.room.*
import android.content.Context
import com.example.mydiagnosis.database.dao.*
import com.example.mydiagnosis.model.*

@Database(entities = [Symptom::class, LabTest::class, RiskFactor::class, Mention::class, Condition::class, Evidence::class], version = 5, exportSchema = false)
@TypeConverters(Converters::class)
abstract class DiagnosisRoomDatabase : RoomDatabase() {

    abstract fun symptomsDao() : SymptomsDao

    abstract fun labTestsDao() : LabTestsDao

    abstract fun riskFactorsDao() : RiskFactorsDao

    abstract fun mentionsDao() : MentionsDao

    abstract fun conditionsDao() : ConditionsDao

    abstract fun evidencesDao() : EvidencesDao

    companion object {

        private var dbInstance : DiagnosisRoomDatabase? = null

        @Synchronized
        fun getDatabase(context : Context) : DiagnosisRoomDatabase? {

            if(dbInstance == null) {

                dbInstance = Room.databaseBuilder(context.applicationContext, DiagnosisRoomDatabase::class.java, "DiagnosisDatabase").
                    fallbackToDestructiveMigration().build()

            }

            return dbInstance
        }

        fun destroyInstance() {
            dbInstance = null
        }

    }

}