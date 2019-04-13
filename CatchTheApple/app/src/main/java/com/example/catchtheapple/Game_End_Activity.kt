package com.example.catchtheapple

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import kotlinx.android.synthetic.main.activity_game_.*

class Game_End_Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game__end_)
        var game_end =intent
        var name=game_end.getStringExtra("name")
        var score=game_end.getIntExtra("score",0)
        textView.text=name+", your score is "+score
    }
    fun retry(view :View){
        var main=Intent(applicationContext,MainActivity::class.java)
        startActivity(main)
    }
}
