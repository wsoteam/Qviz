package com.bcobsop.qvizapp.utils

import com.bcobsop.qvizapp.model.QuestionsHolder
import com.google.firebase.database.FirebaseDatabase

object FBDBWorker {

     fun createNewDirectory(questionsHolder: QuestionsHolder, path : String) {
        FirebaseDatabase
            .getInstance("https://quiz-58ba1-default-rtdb.firebaseio.com/")
            .reference
            .child(path)
            .setValue(questionsHolder)
            .addOnSuccessListener {
            }
    }
}