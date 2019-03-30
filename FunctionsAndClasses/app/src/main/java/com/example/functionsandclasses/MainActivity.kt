package com.example.functionsandclasses

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
var age:Int?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }
    fun test(){
        println("test")
    }
    fun sum(a:Int,b:Int):Int{
        return a+b;
    }
    fun helloWorld(view: View){
    textV.text="Hello World"
    }
    fun makeSimpson(view: View){
        val name=nameText.text.toString()
        if(!ageText.text.toString().equals(""))
            age=ageText.text.toString().toInt()
        else
            age=0
        val job=jobText.text.toString()

      var simpson=Simpson(name,age,job)
        textV.text="Name: "+simpson.name+" Age: "+simpson.age+" Job: "+simpson.job
        println("ddd")
    }
}
