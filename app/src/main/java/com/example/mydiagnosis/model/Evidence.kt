package com.example.mydiagnosis.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.support.annotation.NonNull

@Entity(tableName = "Evidences")
class Evidence() {

    @ColumnInfo(name = "evidenceId")
    @PrimaryKey
    @NonNull
    private var id : String = ""

    @ColumnInfo(name = "choice_id")
    @NonNull
    private var choiceId : String = ""

    @ColumnInfo(name = "initial")
    @NonNull
    private var initial : Boolean = false

    fun setId(id : String) {
        this.id = id
    }

    fun getId() : String {
        return id
    }

    fun setChoiceId(choiceId : String) {
        this.choiceId = choiceId
    }

    fun getChoiceId() : String {
        return choiceId
    }

    fun setInitial(initial : Boolean) {
        this.initial = initial
    }

    fun getInitial() : Boolean {
        return initial
    }

}