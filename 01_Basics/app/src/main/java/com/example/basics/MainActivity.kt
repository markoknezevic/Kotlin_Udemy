package com.example.basics

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var a=5
        var b=2
        var c: Int=4
        var x:Double=4.2
        println(a+b)

        val s="dd"//Konstanta
        val myarray= arrayOfNulls<Int>(4)
        myarray[0]=2;
        myarray[1]=2;
        myarray[2]=22313;
        myarray[3]=2;
        println(myarray[2])
    }
}
