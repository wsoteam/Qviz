package com.bcobsop.qvizapp.presentation.game

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.bcobsop.qvizapp.R
import com.bcobsop.qvizapp.model.Question
import com.bcobsop.qvizapp.presentation.game.controller.AnswerAdapter
import com.bcobsop.qvizapp.utils.holder.QuestionsBinder
import kotlinx.android.synthetic.main.game_activity.*

class GameActivity : AppCompatActivity(R.layout.game_activity) {

    private lateinit var vm: GameVM


    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)

        vm = ViewModelProviders.of(this).get(GameVM::class.java)

        rvAnswers.layoutManager = GridLayoutManager(this, 2)
    }

    override fun onResume() {
        super.onResume()
        vm.getMoney().observe(this, Observer {
            tvMoney.text = it.toString()
        })
        vm.getQuest().observe(this, Observer {
            refreshUI(it)
        })
    }

    private fun refreshUI(question: Question) {
        rvAnswers.adapter = AnswerAdapter(question, object : AnswerCallback{
            override fun answered(isRightAnswer: Boolean) {

            }
        })
        tvQuest.text = question.quest
        tvNumber.text = "Question ${question.currentNumber + 1}"
    }

    override fun onPause() {
        super.onPause()
        vm.getMoney().removeObservers(this)
    }
}