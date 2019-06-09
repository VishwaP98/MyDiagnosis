package com.example.mydiagnosis.viewholder

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.example.mydiagnosis.R

class MentionViewHolder(item : View) : RecyclerView.ViewHolder(item) {

    private val typeIcon = item.findViewById<ImageView>(R.id.mention_type_icon)
    private val mentionName = item.findViewById<TextView>(R.id.mention_txt_title)
    private val mentionChoice = item.findViewById<TextView>(R.id.mention_txt_message)

    fun getTypeIcon() : ImageView {
        return typeIcon
    }

    fun getMentionName() : TextView {
        return mentionName
    }

    fun getMentionChoice() : TextView {
        return mentionChoice
    }

}