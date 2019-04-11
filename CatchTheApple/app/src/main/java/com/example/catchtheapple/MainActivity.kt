package com.example.catchtheapple

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    fun start_game(view:View){
        val intent_menu= Intent(applicationContext,Game_Activity::class.java)
        val name:String = txtName.text.toString()
        val time:Long = (txtTime.text.toString()).toLong()
        intent_menu.putExtra("name",name)
        intent_menu.putExtra("time",time)
        startActivity(intent_menu)
    }
}
