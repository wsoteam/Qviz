package com.bcobsop.qvizapp.model

import java.io.Serializable

data class Question(
    var quest: String,
    var answer: String,
    var firstBadAnswer: String,
    var secondBadAnswer: String,
    var thirdBadAnswer: String,
    var currentNumber : Int = 0
) : Serializable
