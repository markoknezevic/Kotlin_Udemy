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
        val myNumbers= intArrayOf(1,2,3,4,5)
        myNumbers.set(1,0)
        println(myNumbers[2])

        val mylist=ArrayList<Int>()
        mylist.add(1)
        mylist.add(2)

        val mySet=HashSet<Int>()
        mySet.add(2)
        mySet.add(3)

        val myMap=HashMap<String,String>();
        myMap.put("key","marko")
        myMap.put("key","ivan")
        println(myMap["key"])
    }
}
