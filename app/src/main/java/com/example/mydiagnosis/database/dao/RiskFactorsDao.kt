package com.example.mydiagnosis.database.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.example.mydiagnosis.model.RiskFactor

@Dao
interface RiskFactorsDao {

    @Query("SELECT * from RiskFactors")
    fun getAllRiskFactors() : List<RiskFactor>

    @Query("SELECT * from RiskFactors where id = :id")
    fun getRiskFactorWithId(id : String) : RiskFactor

    @Insert
    fun insert(riskFactors : List<RiskFactor>)

    @Query("DELETE FROM RiskFactors")
    fun deleteAll()

}