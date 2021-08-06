package com.bcobsop.qvizapp.presentation.game.dialogs

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.bcobsop.qvizapp.R
import com.bcobsop.qvizapp.utils.PreferenceProvider
import kotlinx.android.synthetic.main.win_dialog.*

class WinDialog : DialogFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.win_dialog, container, false)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(0))
        isCancelable = false
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        tvScore.text = PreferenceProvider.money.toString()

        btnClose.setOnClickListener {
            (activity as Callbacks).win()
        }
    }

    interface Callbacks{
        fun win()
    }
}