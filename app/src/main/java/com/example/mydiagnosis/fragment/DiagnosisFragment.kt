package com.example.mydiagnosis.fragment

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.annotation.Nullable
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.mydiagnosis.R
import com.example.mydiagnosis.adapter.ConditionsAdapter
import com.example.mydiagnosis.adapter.MentionsAdapter
import com.example.mydiagnosis.model.Condition
import com.example.mydiagnosis.viewmodel.DiagnosisResultViewModel
import com.example.mydiagnosis.viewmodel.UserViewModel

class DiagnosisFragment : Fragment() {

    private lateinit var diagnosisResultViewModel: DiagnosisResultViewModel
    private lateinit var userViewModel: UserViewModel

    private lateinit var recyclerView: RecyclerView

    private fun onConditionsFetched(conditions : List<Condition>, recyclerView: RecyclerView) {

        recyclerView.adapter = ConditionsAdapter(requireContext(), conditions.toMutableList(), this.layoutInflater, diagnosisResultViewModel)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

    }

    override fun onCreate(@Nullable savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        diagnosisResultViewModel = ViewModelProviders.of(this).get(DiagnosisResultViewModel::class.java)
        userViewModel = ViewModelProviders.of(this).get(UserViewModel::class.java)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.diagnosis_results, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val diagnoseButton = view.findViewById<Button>(R.id.diagnose_button)
        recyclerView = view.findViewById(R.id.recycler_view_diagnosis)

        recyclerView.visibility = View.GONE

        diagnosisResultViewModel.conditions.observe(this, Observer {

            it?.let{ onConditionsFetched(it, recyclerView) }

        })

        val localContext : Context? = activity

        if(localContext == null) {
            return
        }

        diagnoseButton.setOnClickListener {

            diagnoseButton.visibility = View.GONE
            recyclerView.visibility = View.VISIBLE

            val userAge = userViewModel.getUserAge(localContext)
            val userGender = userViewModel.getUserGender(localContext)
            diagnosisResultViewModel.getDiagnosis(localContext, userAge, userGender)

        }


    }

}