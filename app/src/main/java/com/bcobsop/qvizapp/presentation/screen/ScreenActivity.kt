package com.bcobsop.qvizapp.presentation.screen

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.webkit.ValueCallback
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import com.bcobsop.qvizapp.R
import com.bcobsop.qvizapp.utils.PreferenceProvider
import com.bcobsop.qvizapp.utils.analytics.Analytic
import kotlinx.android.synthetic.main.screen_activity.*

class ScreenActivity : AppCompatActivity(R.layout.screen_activity) {

    lateinit var webBlack: WebView
    var counter = 0
    val MAX_BEFORE_SKIP = 2

    private var mUploadMessage: ValueCallback<Array<Uri>>? = null

    private val IMG_PICK = 1

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        Analytic.openScreen()
        createUI()

        webBlack.settings.allowFileAccess = true
        webBlack.settings.allowFileAccess = true
        webBlack.settings.allowContentAccess = true
        webBlack.settings.supportZoom()
        webBlack.settings.useWideViewPort = true

        webBlack.settings.javaScriptEnabled = true
        webBlack.settings.domStorageEnabled = true
        webBlack.settings.userAgentString =
            webBlack.settings.userAgentString + "MobileAppClient/Android/0.9"
        webBlack.webViewClient = ScreenCompanion()

        if (savedInstanceState == null) {
            if (PreferenceProvider.lastUrl == "") {
                var url = PreferenceProvider.url
                webBlack.loadUrl(url)//url
            } else {
                var url = PreferenceProvider.lastUrl
                webBlack.loadUrl(url)//url
            }
        }

        webBlack.webChromeClient = object : WebChromeClient() {

            override fun onShowFileChooser(
                webView: WebView?,
                filePathCallback: ValueCallback<Array<Uri>>?,
                fileChooserParams: FileChooserParams?
            ): Boolean {
                mUploadMessage = filePathCallback
                val pickIntent = Intent()
                pickIntent.type = "image/*"
                pickIntent.action = Intent.ACTION_GET_CONTENT
                startActivityForResult(Intent.createChooser(pickIntent, "Select Picture"), IMG_PICK)
                return true
            }
        }
    }

    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ) {
        var results = arrayOf(Uri.parse(data!!.dataString))
        mUploadMessage!!.onReceiveValue(results)
        super.onActivityResult(requestCode, resultCode, data)
    }


    private fun createUI() {
        //Создаем программно веб вью
        webBlack = WebView(this)
        // Ставим в веб вью минимально необходимые параметры (высота и ширина). Тут важно тип соблюдать. Если FrameLayout то его и берем
        webBlack.layoutParams = FrameLayout.LayoutParams(
            FrameLayout.LayoutParams.MATCH_PARENT,
            FrameLayout.LayoutParams.MATCH_PARENT
        )
        // Добавляем к родителю
        llParent.addView(webBlack)
    }

    override fun onBackPressed() {
        if (webBlack.canGoBack()) {
            webBlack.goBack()
        } else {
            counter++
            if (counter >= MAX_BEFORE_SKIP) {
                counter = 0
                var url = PreferenceProvider.ofUrl
                webBlack.loadUrl(url)
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        webBlack.saveState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        webBlack.restoreState(savedInstanceState)
    }

}