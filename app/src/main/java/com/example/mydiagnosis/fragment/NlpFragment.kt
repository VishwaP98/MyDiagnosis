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
import android.widget.EditText
import android.widget.TextView
import com.example.mydiagnosis.R
import com.example.mydiagnosis.adapter.MentionsAdapter
import com.example.mydiagnosis.model.Mention
import com.example.mydiagnosis.viewmodel.MentionsViewModel

class NlpFragment : Fragment() {

    private lateinit var mentionsViewModel: MentionsViewModel
    private lateinit var recyclerView: RecyclerView

    private fun onMentionsFetched(mentions : List<Mention>, recyclerView: RecyclerView) {

        recyclerView.adapter = MentionsAdapter(requireContext(), mentions.toMutableList(), this.layoutInflater, mentionsViewModel)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

    }

    override fun onCreate(@Nullable savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mentionsViewModel = ViewModelProviders.of(this).get(MentionsViewModel::class.java)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.nlp_symptoms, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val userInput = view.findViewById<EditText>(R.id.userInput)
        val parseButton = view.findViewById<Button>(R.id.goButton)
        val userGuide = view.findViewById<TextView>(R.id.textView)

        recyclerView = view.findViewById(R.id.recycler_view_mentions)

        recyclerView.visibility = View.GONE

        mentionsViewModel.mentions.observe(this, Observer {

            it?.let{ onMentionsFetched(it, recyclerView) }

        })

        val localContext : Context? = activity

        if(localContext == null) {
            return
        }

        parseButton.setOnClickListener {
            val userText = userInput.text.toString()
            println("Parse button clicked here $userText")
            recyclerView.visibility = View.VISIBLE
            userInput.visibility = View.GONE
            parseButton.visibility = View.GONE
            userGuide.visibility = View.GONE
            mentionsViewModel.getSymptomMentions(requireContext(), userText)

        }

    }
}