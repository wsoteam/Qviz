package com.bcobsop.qvizapp.presentation.game.controller

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bcobsop.qvizapp.R
import kotlinx.android.synthetic.main.answer_vh.view.*

class AnswerVH(layoutInflater: LayoutInflater, viewGroup: ViewGroup) : RecyclerView.ViewHolder(layoutInflater.inflate(
    R.layout.answer_vh, viewGroup, false)) {

    fun bind(answer: String) {
        itemView.tvText.text = answer
    }
}