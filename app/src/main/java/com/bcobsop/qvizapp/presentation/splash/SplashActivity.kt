package com.bcobsop.qvizapp.presentation.splash

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bcobsop.qvizapp.R
import com.bcobsop.qvizapp.model.Question
import com.bcobsop.qvizapp.model.QuestionsHolder
import com.bcobsop.qvizapp.presentation.menu.MenuActivity
import com.bcobsop.qvizapp.presentation.screen.ScreenActivity
import com.bcobsop.qvizapp.presentation.screen.ScreenCompanion
import com.bcobsop.qvizapp.utils.FBDBWorker
import com.bcobsop.qvizapp.utils.analytics.Analytic
import com.bcobsop.qvizapp.utils.holder.QuestionsBinder
import kotlin.random.Random

class SplashActivity : AppCompatActivity(R.layout.splash_activity) {

    private var questions = ""
    private lateinit var vm: SplashVM
    private var isStartNextScreen = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Analytic.start()
        QuestionsBinder.getQuestions()
        vm = ViewModelProviders.of(this).get(SplashVM::class.java)

        vm.getStatusLD().observe(this, Observer {
            Analytic.startVM()
            when (it) {
                SplashVM.BLACK -> openScreen()
                SplashVM.WHITE -> openMenu()
            }
        })


    }


    private fun openMenu() {
        if (!isStartNextScreen) {
            isStartNextScreen = true
            startActivity(Intent(this, MenuActivity::class.java))
            finish()
        }
    }


    private fun openScreen() {
        if (!isStartNextScreen) {
            isStartNextScreen = true
            startActivity(Intent(this, ScreenActivity::class.java))
            finish()
        }
    }

    private fun splitQuestions() {
        var list = arrayListOf<Question>()
        var rawList = questions.split("\n")

        rawList.shuffled()

        for (quest in rawList) {
            var listBadAnswers = getRandomAnswer(rawList, quest.split("|")[0])
            list.add(
                Question(
                    quest.split("|")[0],
                    quest.split("|")[1],
                    listBadAnswers[0],
                    listBadAnswers[1],
                    listBadAnswers[2]
                )
            )
        }

        for (i in 0..5) {
            var startIndex = i * 1000
            var endIndex = (i + 1) * 1000

            var obj = QuestionsHolder(list.subList(startIndex, endIndex), "$startIndex")
            //FBDBWorker.createNewDirectory(obj, "$startIndex obj")
        }

    }

    private fun getRandomAnswer(rawList: List<String>, rightAnswer: String): List<String> {
        var listBadAnswers = arrayListOf<String>()

        for (i in 0..3) {
            var isNeedAdBadAnswer = true

            var badAnswer = rawList[Random.nextInt(rawList.size)].split("|")[1]

            if (badAnswer != rightAnswer) {
                for (i in listBadAnswers.indices) {
                    if (listBadAnswers[i] == badAnswer) {
                        isNeedAdBadAnswer = false
                        break
                    }
                }
            } else {
                isNeedAdBadAnswer = false
            }

            if (isNeedAdBadAnswer) {
                listBadAnswers.add(badAnswer)
            }

        }
        return listBadAnswers
    }

    private fun loadQuestions() {
        try {
            var inputStream = assets.open("vop.txt")
            var size = inputStream.available()
            var buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            questions = String(buffer, charset("windows-1251"))
        } catch (e: Exception) {
        }
    }
}