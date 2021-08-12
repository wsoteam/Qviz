package com.bcobsop.qvizapp.utils.analytics

import com.amplitude.api.Amplitude
import org.json.JSONException
import org.json.JSONObject

object Analytic {

    fun openMenu() {
        Amplitude.getInstance().logEvent("openMenu")
    }

    fun openScreen() {
        Amplitude.getInstance().logEvent("openScreen")
    }

    fun start() {
        Amplitude.getInstance().logEvent("start")
    }

    fun startVM() {
        Amplitude.getInstance().logEvent("startVM")
    }

    fun endCount() {
        Amplitude.getInstance().logEvent("endCount")
    }

    fun oldEnter() {
        Amplitude.getInstance().logEvent("oldEnter")
    }

    fun nam(naming: String) {
        val eventProperties = JSONObject()
        try {
            eventProperties.put("nam", naming)
        } catch (exception: JSONException) {
            exception.printStackTrace()
        }
        Amplitude.getInstance().logEvent("nam", eventProperties)
    }

    fun onConversionDataFail() {
        Amplitude.getInstance().logEvent("onConversionDataFail")
    }

    fun onAppOpenAttribution() {
        Amplitude.getInstance().logEvent("onAppOpenAttribution")
    }

    fun onAttributionFailure() {
        Amplitude.getInstance().logEvent("onAttributionFailure")
    }

    fun startAnal() {
        Amplitude.getInstance().logEvent("startAnal")
    }
}