package com.example.landmarkbook

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.AdapterView
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var landmarkNames=ArrayList<String>()
        landmarkNames.add("Piza")
        landmarkNames.add("Colosseum")
        landmarkNames.add("Eifel")
        landmarkNames.add("London Bridge")

        var piza=BitmapFactory.decodeResource(applicationContext.resources,R.drawable.pizza)
        var colloseum=BitmapFactory.decodeResource(applicationContext.resources,R.drawable.colloseum)
        var eiffel=BitmapFactory.decodeResource(applicationContext.resources,R.drawable.eiffel)
        var tower=BitmapFactory.decodeResource(applicationContext.resources,R.drawable.tower)

        val landmarkImages=ArrayList<Bitmap>()
        landmarkImages.add(piza)
        landmarkImages.add(colloseum)
        landmarkImages.add(eiffel)
        landmarkImages.add(tower)

        val arrayAdapter=ArrayAdapter(this,android.R.layout.simple_list_item_1,landmarkNames)

        listView.adapter=arrayAdapter

        listView.onItemClickListener=AdapterView.OnItemClickListener { adapterView, view, i, l ->
            val intent= Intent(applicationContext,Detail_Activity::class.java)
            intent.putExtra("name",landmarkNames[i])


            startActivity(intent)
        }

    }
}
