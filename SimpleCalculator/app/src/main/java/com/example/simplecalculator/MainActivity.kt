package com.example.simplecalculator

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun sum(view:View){
        var a=FirstN.text.toString().toDouble()
        var b=SecondN.text.toString().toDouble()

        var c=a+b
        textView.text=c.toString()
    }

    fun dec(view:View){
        var a=FirstN.text.toString().toDouble()
        var b=SecondN.text.toString().toDouble()

        var c=a-b
        textView.text=c.toString()
    }
    fun dev(view:View){
        var a=FirstN.text.toString().toDouble()
        var b=SecondN.text.toString().toDouble()

        var c=a/b
        textView.text=c.toString()
    }
    fun mul(view:View){
        var a=FirstN.text.toString().toDouble()
        var b=SecondN.text.toString().toDouble()

        var c=a*b
        textView.text=c.toString()
    }
}
