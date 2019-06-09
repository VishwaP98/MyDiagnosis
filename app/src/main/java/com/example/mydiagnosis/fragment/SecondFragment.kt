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
import com.example.mydiagnosis.R
import com.example.mydiagnosis.adapter.LabTestsAdapter
import com.example.mydiagnosis.model.LabTest
import com.example.mydiagnosis.viewmodel.LabTestsViewModel

class SecondFragment : Fragment() {

    private lateinit var labTestsViewModel: LabTestsViewModel
    private lateinit var recyclerView: RecyclerView

    private fun onLabTestsFetched(labTests : List<LabTest>, recyclerView: RecyclerView) {

        recyclerView.adapter = LabTestsAdapter(requireContext(), labTests.toMutableList(), this.layoutInflater, labTestsViewModel)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

    }

    override fun onCreate(@Nullable savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        labTestsViewModel = ViewModelProviders.of(this).get(LabTestsViewModel::class.java)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.second_fragment, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        recyclerView = view.findViewById(R.id.recyclerViewLabTests)

        labTestsViewModel.labTests.observe(this, Observer {

            it?.let{ onLabTestsFetched(it, recyclerView) }

        })

        val localContext : Context? = activity

        if(localContext == null) {
            return
        }

        labTestsViewModel.fetchLabTests(localContext)

    }

}