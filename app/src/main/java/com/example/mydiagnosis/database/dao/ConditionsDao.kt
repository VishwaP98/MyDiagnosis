package com.example.mydiagnosis.database.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.example.mydiagnosis.model.Condition
import com.example.mydiagnosis.model.Conditions

@Dao
interface ConditionsDao {

    @Insert
    fun insertConditions(conditions: List<Condition>)

    @Query("SELECT * from Conditions")
    fun getAllConditions() : List<Condition>

    @Query("DELETE FROM Conditions")
    fun deleteAll()

}