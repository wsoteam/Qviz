package com.bcobsop.qvizapp.presentation.game.controller

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bcobsop.qvizapp.model.Question
import com.bcobsop.qvizapp.presentation.game.AnswerCallback

class AnswerAdapter(val question: Question, val answerCallback: AnswerCallback) : RecyclerView.Adapter<AnswerVH>() {

    private var listAnswers: ArrayList<String> = arrayListOf()
    private var badAnswerIndex = -1
    private var rightAnswerIndex = -1

    init {
        listAnswers.add(question.answer)
        listAnswers.add(question.firstBadAnswer)
        listAnswers.add(question.secondBadAnswer)
        listAnswers.add(question.thirdBadAnswer)
        listAnswers.shuffle()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnswerVH {
        val li = LayoutInflater.from(parent.context)
        return AnswerVH(li, parent, object : ClickAnswer {
            override fun clickAnswer(position: Int) {
                bindAnswer(position)
            }
        })
    }

    private fun bindAnswer(position: Int) {
        var isAnsweredRight = question.answer == listAnswers[position]
        answerCallback.answered(isAnsweredRight)

        rightAnswerIndex = getRightAnswerPosition()

        if (!isAnsweredRight) {
            badAnswerIndex = position
        }

        notifyDataSetChanged()
    }

    private fun getRightAnswerPosition(): Int {
        var position = 0
        for(i in listAnswers.indices){
            if (listAnswers[i] == question.answer){
                position = i
            }
        }
        return position
    }

    override fun onBindViewHolder(holder: AnswerVH, position: Int) {
        holder.bind(listAnswers[position], getState(position))
    }

    private fun getState(position: Int): Int {
        return when (position) {
            rightAnswerIndex -> {
                AnswerVH.RIGHT_ANSWER
            }
            badAnswerIndex -> {
                AnswerVH.BAD_ANSWER
            }
            else -> {
                AnswerVH.DEFAULT_ANSWER
            }
        }
    }

    override fun getItemCount(): Int {
        return 4
    }
}