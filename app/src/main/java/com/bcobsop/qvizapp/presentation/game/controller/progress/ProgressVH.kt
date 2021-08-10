package com.bcobsop.qvizapp.presentation.game.controller.progress

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bcobsop.qvizapp.R
import kotlinx.android.synthetic.main.progress_vh.view.*

class ProgressVH(layoutInflater: LayoutInflater, viewGroup: ViewGroup) : RecyclerView.ViewHolder(
    layoutInflater.inflate(
        R.layout.progress_vh, viewGroup, false
    )
) {
    fun bind(isEnabled: Boolean) {
        itemView.ivProgress.isEnabled = isEnabled
    }
}