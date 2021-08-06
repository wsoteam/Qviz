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

    fun bind(answer: String, state : Int) {
        itemView.tvText.text = answer
        when(state){
            RIGHT_ANSWER -> {
                itemView.ivBack.setImageResource(R.color.right)
                itemView.tvText.setTextColor(itemView.resources.getColor(R.color.textColorAnswer))
            }
            BAD_ANSWER -> {
                itemView.ivBack.setImageResource(R.color.bad)
                itemView.tvText.setTextColor(itemView.resources.getColor(R.color.textColorAnswer))
            }
            else -> {
                itemView.ivBack.setImageResource(R.color.start)
                itemView.tvText.setTextColor(itemView.resources.getColor(R.color.textColorDef))
            }
        }
    }

    companion object{
        const val RIGHT_ANSWER = 1
        const val BAD_ANSWER = 0
        const val DEFAULT_ANSWER = -1
    }
}