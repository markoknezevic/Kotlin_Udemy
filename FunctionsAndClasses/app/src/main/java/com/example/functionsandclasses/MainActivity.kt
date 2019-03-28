package com.example.functionsandclasses

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        test()
        println(sum(1,2))
    }
    fun test(){
        println("test")
    }
    fun sum(a:Int,b:Int):Int{
        return a+b;
    }
    fun helloWorld(view: View){
    textView.text="Hello World"
    }
}
