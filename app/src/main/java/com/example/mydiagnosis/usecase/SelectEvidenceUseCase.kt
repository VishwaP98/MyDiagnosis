package com.example.mydiagnosis.usecase

import android.content.Context
import com.example.mydiagnosis.database.DiagnosisRoomDatabase
import com.example.mydiagnosis.model.Evidence
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SelectEvidenceUseCase {

    suspend fun run(context : Context, evidenceId : String, evidenceChoiceId : String) {

        CoroutineScope(Dispatchers.IO).launch {

            val database = DiagnosisRoomDatabase.getDatabase(context)

            var isInitial = false

            val evidence = Evidence()

            evidence.setId(evidenceId)
            evidence.setChoiceId(evidenceChoiceId)

            if(database?.evidencesDao()?.getEvidenceIfPresent(evidenceId) != null) {

                evidence.setInitial(true)

                database.evidencesDao().updateEvidence(evidence)
            } else {

                evidence.setInitial(isInitial)

                database?.evidencesDao()?.insertEvidence(evidence)
            }

        }

    }

}