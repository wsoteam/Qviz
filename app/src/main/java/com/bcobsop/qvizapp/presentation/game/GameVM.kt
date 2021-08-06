package com.bcobsop.qvizapp.presentation.game

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.bcobsop.qvizapp.model.Question
import com.bcobsop.qvizapp.model.QuestionsHolder
import com.bcobsop.qvizapp.utils.PreferenceProvider
import com.bcobsop.qvizapp.utils.holder.QuestionsBinder

class GameVM(application: Application) : AndroidViewModel(application) {

    private var money: MutableLiveData<Int>? = null
    private var quest: MutableLiveData<Question>? = null

    private var questionsHolder: QuestionsHolder? = null
    private var currentIndex = 0


    fun getMoney(): MutableLiveData<Int> {
        if (money == null) {
            money = MutableLiveData()
            bindMoney()
        }
        return money!!
    }


    fun getQuest(): MutableLiveData<Question> {
        if (questionsHolder == null){
            loadQuestions()
        }
        if (quest == null) {
            quest = MutableLiveData()
            refreshQuest()
        }
        return quest!!
    }

    private fun refreshQuest() {
        if (currentIndex >= QuestionsBinder.getQuestions().listQuestions.size) {
            currentIndex = 0
        }
        var question = questionsHolder!!.listQuestions[currentIndex]
        question.currentNumber = currentIndex
        quest!!.value = question
        currentIndex++
    }

    private fun bindMoney() {
        money!!.value = PreferenceProvider.money
    }

    private fun loadQuestions() {
        if (questionsHolder == null) {
            questionsHolder = QuestionsBinder.getQuestions()
        }
    }
}