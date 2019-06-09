package com.example.mydiagnosis.fragment

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.annotation.Nullable
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mydiagnosis.R
import com.example.mydiagnosis.adapter.SymptomsAdapter
import com.example.mydiagnosis.model.Symptom
import com.example.mydiagnosis.viewmodel.SymptomViewModel


class FirstFragment() : Fragment() {

    private lateinit var symptomViewModel : SymptomViewModel
    private lateinit var recyclerView: RecyclerView

    private fun onSymptomsFetched(symptoms : List<Symptom>, recyclerView: RecyclerView) {

        val dividerItemDecoration = DividerItemDecoration(recyclerView.context, LinearLayoutManager(requireContext()).orientation)
        dividerItemDecoration.setDrawable(
            ContextCompat.getDrawable(
                requireContext(),
                R.drawable.draw_recyclerview
            )!!
        )

        recyclerView.addItemDecoration(dividerItemDecoration)

        recyclerView.adapter = SymptomsAdapter(requireContext(), symptoms, this.layoutInflater, symptomViewModel)

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

    override fun onCreate(@Nullable savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        symptomViewModel = ViewModelProviders.of(this).get(SymptomViewModel::class.java)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.first_fragment, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        recyclerView = view.findViewById(R.id.recycler_view)

        symptomViewModel.symptomsFound.observe(this, Observer {

            it?.let{ onSymptomsFetched(it, recyclerView) }

        })

        val localContext : Context? = activity

        if(localContext == null) {
            return
        }

        symptomViewModel.fetchSymptoms(localContext)

    }

}