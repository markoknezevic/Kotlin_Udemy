package com.example.travel_book

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val manuInflater=menuInflater
        manuInflater.inflate(R.menu.map,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if(item?.itemId == R.id.map)
        {
            val  intent = Intent(applicationContext,MapsActivity :: class.java)
            startActivity(intent)
        }
        return super.onOptionsItemSelected(item)
    }
}
