package com.bcobsop.qvizapp.utils

import android.content.Context
import android.content.SharedPreferences
import com.bcobsop.qvizapp.App

object PreferenceProvider {

    private fun getInstance(): SharedPreferences? {
        val sp = App.getInstance().getSharedPreferences(
            App.getInstance().packageName + ".SharedPreferences",
            Context.MODE_PRIVATE
        )
        return sp
    }

    private fun editor(put: (SharedPreferences.Editor?) -> SharedPreferences.Editor?) = put(getInstance()?.edit())?.apply()



    private const val MONEY_TAG = "MONEY_TAG"

    var money: Int
        get() = getInstance()?.getInt(MONEY_TAG, 1000)!!
        set(value) = editor { it?.putInt(MONEY_TAG, value) }!!
}