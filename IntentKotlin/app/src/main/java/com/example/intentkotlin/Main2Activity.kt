package com.example.intentkotlin

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
class Main2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
    }
    fun changeAct(view:View){
        val intent= Intent(applicationContext,MainActivity::class.java)
        startActivity(intent)
    }
}
