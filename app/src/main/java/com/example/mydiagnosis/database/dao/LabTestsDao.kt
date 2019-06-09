package com.example.mydiagnosis.database.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.example.mydiagnosis.model.LabTest

@Dao
interface LabTestsDao {

    @Query("SELECT * from LabTests")
    fun getAllLabTests() : MutableList<LabTest>

    @Query("SELECT * from LabTests where id = :id")
    fun getLabTestWithId(id : String) : LabTest

    @Insert
    fun insert(labTests : List<LabTest>)

    @Query("DELETE FROM LabTests")
    fun deleteAll()

}