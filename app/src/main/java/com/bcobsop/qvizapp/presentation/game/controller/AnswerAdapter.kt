package com.bcobsop.qvizapp.presentation.game.controller

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bcobsop.qvizapp.model.Question

class AnswerAdapter(val question: Question) : RecyclerView.Adapter<AnswerVH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnswerVH {
        val li = LayoutInflater.from(parent.context)
        return AnswerVH(li, parent)
    }

    override fun onBindViewHolder(holder: AnswerVH, position: Int) {
        holder.bind(question.answer)
    }

    override fun getItemCount(): Int {
        return 4
    }
}