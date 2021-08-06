package com.bcobsop.qvizapp.presentation.menu

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bcobsop.qvizapp.R
import com.bcobsop.qvizapp.presentation.game.GameActivity
import com.bcobsop.qvizapp.utils.holder.QuestionsBinder
import kotlinx.android.synthetic.main.menu_activity.*

class MenuActivity : AppCompatActivity(R.layout.menu_activity) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        btnPlay.setOnClickListener {
            QuestionsBinder.shuffle()
            startActivity(Intent(this, GameActivity::class.java))
        }

        btnExit.setOnClickListener {
            finishAffinity()
        }
    }


}