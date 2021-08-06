package com.bcobsop.qvizapp.presentation.game

import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.bcobsop.qvizapp.R
import com.bcobsop.qvizapp.model.Question
import com.bcobsop.qvizapp.presentation.game.controller.AnswerAdapter
import kotlinx.android.synthetic.main.game_activity.*

class GameActivity : AppCompatActivity(R.layout.game_activity) {

    private lateinit var vm: GameVM
    private lateinit var lifesList: ArrayList<ImageView>
    private var countDownTimer: CountDownTimer? = null


    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)

        vm = ViewModelProviders.of(this).get(GameVM::class.java)
        rvAnswers.layoutManager = GridLayoutManager(this, 2)


        lifesList = arrayListOf()
        lifesList.add(ivFirstLife)
        lifesList.add(ivSecondLife)
        lifesList.add(ivThirdLife)
    }

    override fun onResume() {
        super.onResume()
        vm.getMoney().observe(this, Observer {
            tvMoney.text = it.toString()
        })

        vm.getQuest().observe(this, Observer {
            refreshUI(it)
        })

        vm.getLifes().observe(this, Observer {
            refreshLifes(it)
        })
    }

    private fun refreshLifes(it: Int) {
        when (it) {
            2 -> ivThirdLife.visibility = View.INVISIBLE
            1 -> ivSecondLife.visibility = View.INVISIBLE
            0 -> ivFirstLife.visibility = View.INVISIBLE
        }
        if (it <= 0) {
            endGame()
        }
    }

    private fun endGame() {
        Toast.makeText(this, "LOL", Toast.LENGTH_LONG).show()
    }


    private fun refreshUI(question: Question) {
        rvAnswers.adapter = AnswerAdapter(question, object : AnswerCallback {
            override fun answered(isRightAnswer: Boolean) {
                bindAnswer(isRightAnswer)
            }
        })
        tvQuest.text = question.quest
        tvNumber.text = "Question ${question.currentNumber + 1}"
    }

    private fun bindAnswer(isRightAnswer: Boolean) {
        countDownTimer = object : CountDownTimer(1500, 100) {
            override fun onTick(millisUntilFinished: Long) {
            }

            override fun onFinish() {
                vm.getNextQuestion(isRightAnswer)
                countDownTimer = null
            }
        }
        countDownTimer!!.start()
    }


    override fun onPause() {
        super.onPause()
        vm.getMoney().removeObservers(this)
        vm.getLifes().removeObservers(this)
        vm.getQuest().removeObservers(this)
    }
}