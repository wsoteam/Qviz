package com.bcobsop.qvizapp.presentation.game.dialogs

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.bcobsop.qvizapp.R
import kotlinx.android.synthetic.main.defeat_dialog.*

class DefeatDialog : DialogFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.defeat_dialog, container, false)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(0))
        isCancelable = false
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        btnClose.setOnClickListener {
            (activity as Callbacks).exit()
        }
    }

    interface Callbacks{
        fun exit()
    }
}