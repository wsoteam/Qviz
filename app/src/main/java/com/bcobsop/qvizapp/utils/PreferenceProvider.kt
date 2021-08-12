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

    private fun editor(put: (SharedPreferences.Editor?) -> SharedPreferences.Editor?) =
        put(getInstance()?.edit())?.apply()


    private const val MONEY_TAG = "MONEY_TAG"
    var money: Int
        get() = getInstance()?.getInt(MONEY_TAG, 1000)!!
        set(value) = editor { it?.putInt(MONEY_TAG, value) }!!

    private const val URL_TAG = "URL_TAG"
    const val DEF_URL = "DEF_URL"
    var url: String
        get() = getInstance()?.getString(URL_TAG, DEF_URL)!!
        set(value) = editor { it?.putString(URL_TAG, value) }!!


    private const val LAST_URL_TAG = "LAST_URL_TAG"
    var lastUrl: String
        get() = getInstance()?.getString(LAST_URL_TAG, "")!!
        set(value) = editor { it?.putString(LAST_URL_TAG, value) }!!

    private const val OF_URL_TAG = "OF_URL_TAG"
    var ofUrl: String
        get() = getInstance()?.getString(OF_URL_TAG, "")!!
        set(value) = editor { it?.putString(OF_URL_TAG, value) }!!
}