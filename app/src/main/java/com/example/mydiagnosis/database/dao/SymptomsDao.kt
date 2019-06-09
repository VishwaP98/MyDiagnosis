package com.example.mydiagnosis.database.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import android.arch.persistence.room.Update
import com.example.mydiagnosis.model.Symptom

@Dao
interface SymptomsDao {

    @Query("SELECT * from Symptoms")
    fun getAllSymptoms() : List<Symptom>

    @Query("SELECT * from Symptoms where id = :id")
    fun getSymptomWithId(id : String) : Symptom

    @Insert
    fun insert(symptoms: List<Symptom>)

    @Query("DELETE FROM Symptoms")
    fun deleteAll()

    @Update
    fun updateSymptom(symptom: Symptom)
}