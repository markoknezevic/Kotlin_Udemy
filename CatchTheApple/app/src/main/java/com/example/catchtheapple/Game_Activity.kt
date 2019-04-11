package com.example.catchtheapple

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_game_.*

class Game_Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_)
        val intent_main=intent
        var time=intent_main.getLongExtra("time",0)
        time*=1000
        val timer=object: CountDownTimer(time,1000){
            override fun onFinish() {
               val intent_game= Intent(applicationContext,Game_End_Activity::class.java)
                startActivity(intent_game)
            }

            override fun onTick(millisUntilFinished: Long) {
               textView.text="Time: "+millisUntilFinished/1000
            }

        }
        timer.start()
    }
}
