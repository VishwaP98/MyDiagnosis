package com.example.mydiagnosis.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.mydiagnosis.R
import com.example.mydiagnosis.model.Mention
import com.example.mydiagnosis.model.MentionType
import com.example.mydiagnosis.viewholder.MentionViewHolder
import com.example.mydiagnosis.viewmodel.MentionsViewModel

class MentionsAdapter(val context: Context, val mentions : MutableList<Mention>, val layoutInflater: LayoutInflater,
                      val mentionsViewModel: MentionsViewModel) : RecyclerView.Adapter<MentionViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MentionViewHolder {
        val view = layoutInflater.inflate(R.layout.mention_layout, p0, false)
        return MentionViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mentions.size
    }

    override fun onBindViewHolder(p0: MentionViewHolder, p1: Int) {

        p0.getMentionName().text = mentions[p1].getName()

        p0.getMentionChoice().text = mentions[p1].getChoiceId()

        if(mentions[p1].getType() == MentionType.SYMPTOM) {
            p0.getTypeIcon().setImageResource(R.drawable.symptom_icon)
        } else {
            p0.getTypeIcon().setImageResource(R.drawable.risk_factor)
        }

    }

}