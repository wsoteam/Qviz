package com.bcobsop.qvizapp.utils.holder

import com.bcobsop.qvizapp.App
import com.bcobsop.qvizapp.model.QuestionsHolder
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

object QuestionsBinder {

    private var list : QuestionsHolder? = null
    
    fun getQuestions() : QuestionsHolder{
        if (list == null){
            list = loadQuestions()
        }
        return list!!
    }

    fun shuffle(){
        list!!.listQuestions = list!!.listQuestions.shuffled()
    }

    private fun loadQuestions(): QuestionsHolder? {
        var json: String
        var moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
        var jsonAdapter = moshi.adapter(QuestionsHolder::class.java)
        try {
            var inputStream = App.getInstance().assets.open("bs.json")
            var size = inputStream.available()
            var buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            json = String(buffer, charset("UTF-8"))
            return jsonAdapter.fromJson(json)
        } catch (e: Exception) {
        }
        return null
    }
}