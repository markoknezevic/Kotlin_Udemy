package com.example.landmarkbook

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_detail_2.*

class Detail_Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_2)
        val intent=intent
        val name=intent.getStringExtra("name")
        textView.text=name

    }
}
