package com.bcobsop.qvizapp.presentation.game.controller

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bcobsop.qvizapp.R
import kotlinx.android.synthetic.main.answer_vh.view.*

class AnswerVH(layoutInflater: LayoutInflater, viewGroup: ViewGroup, val clickAnswer: ClickAnswer) : RecyclerView.ViewHolder(layoutInflater.inflate(
    R.layout.answer_vh, viewGroup, false)), View.OnClickListener {

    init {
        itemView.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        clickAnswer.clickAnswer(adapterPosition)
    }

    fun bind(answer: String) {
        itemView.tvText.text = answer
    }
}