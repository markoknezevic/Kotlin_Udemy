package com.example.storingdata

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sharedPerferences=this.getSharedPreferences(" com.example.storingdata", Context.MODE_PRIVATE)
        var age=30
        //sharedPerferences.edit().putInt("userAge",age).apply()
        val s=sharedPerferences.getInt("userAge",0)
        textView.text=s.toString()
        age =31
        sharedPerferences.edit().putInt("userAge",age).apply()
        val s2=sharedPerferences.getInt("userAge",0)
       textView.text=s2.toString()
        sharedPerferences.edit().remove("userAge").apply()
        val s3=sharedPerferences.getInt("userAge",0)
        textView.text=s3.toString()
    }
}
