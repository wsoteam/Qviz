package com.bcobsop.qvizapp.presentation.game.controller.progress

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class ProgressAdapter(val size : Int) : RecyclerView.Adapter<ProgressVH>() {

    var activeNumber = 0

    fun updateProgress(currentProgress : Int){
        activeNumber = currentProgress
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProgressVH {
        val li = LayoutInflater.from(parent.context)
        return ProgressVH(li, parent)
    }

    override fun onBindViewHolder(holder: ProgressVH, position: Int) {
        holder.bind(position <= activeNumber)
    }

    override fun getItemCount(): Int {
        return size
    }
}