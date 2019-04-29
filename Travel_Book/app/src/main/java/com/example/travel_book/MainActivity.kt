package com.example.travel_book

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.google.android.gms.maps.model.LatLng
import kotlinx.android.synthetic.main.activity_main.*

val nameArray = ArrayList<String>()
val locationArray = ArrayList<LatLng>()

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        try {

            val database = openOrCreateDatabase("Places", Context.MODE_PRIVATE, null)
            database.execSQL("CREATE TABLE IF NOT EXISTS places(name VARCHAR,latitude VARCHAR,longitude VARCHAR)")
            val cursor = database.rawQuery("SELECT * FROM places", null)

            val nameIx = cursor.getColumnIndex("name")
            val latitudeIx = cursor.getColumnIndex("latitude")
            val longitudeIx = cursor.getColumnIndex("longitude")

            cursor.moveToFirst()

            nameArray.clear()
            locationArray.clear()


            while (cursor != null) {

                val nameDb = cursor.getString(nameIx)
                val latDb = cursor.getString(latitudeIx).toDouble()
                val lonDb = cursor.getString(longitudeIx).toDouble()

                nameArray.add(nameDb)

                val location = LatLng(latDb,lonDb)

                locationArray.add(location)

                cursor.moveToNext()

            }
        println(nameArray)
            cursor?.close()
        }catch (e: Exception) {
            e.printStackTrace()
        }
        val arrayAdapter = ArrayAdapter(this,android.R.layout.simple_list_item_1,nameArray)
        listView.adapter = arrayAdapter



        listView.onItemClickListener = AdapterView.OnItemClickListener { adapterView, view, position, id ->
            val intent = Intent(applicationContext, MapsActivity :: class.java)

            intent.putExtra("address",nameArray[position])
            intent.putExtra("lat",locationArray[position].latitude)
            intent.putExtra("lon",locationArray[position].longitude)
            intent.putExtra("info","old")
            startActivity(intent)


        }
    }



    override fun onResume() {
        try {

            val database = openOrCreateDatabase("Places", Context.MODE_PRIVATE, null)
            database.execSQL("CREATE TABLE IF NOT EXISTS places(name VARCHAR,latitude VARCHAR,longitude VARCHAR)")
            val cursor = database.rawQuery("SELECT * FROM places", null)

            val nameIx = cursor.getColumnIndex("name")
            val latitudeIx = cursor.getColumnIndex("latitude")
            val longitudeIx = cursor.getColumnIndex("longitude")

            cursor.moveToFirst()

            nameArray.clear()
            locationArray.clear()


            while (cursor != null) {

                val nameDb = cursor.getString(nameIx)
                val latDb = cursor.getString(latitudeIx).toDouble()
                val lonDb = cursor.getString(longitudeIx).toDouble()

                nameArray.add(nameDb)

                val location = LatLng(latDb,lonDb)

                locationArray.add(location)

                cursor.moveToNext()

            }
            println(nameArray)
            cursor?.close()
        }catch (e: Exception) {
            e.printStackTrace()
        }
        val arrayAdapter = ArrayAdapter(this,android.R.layout.simple_list_item_1,nameArray)
        listView.adapter = arrayAdapter



        listView.onItemClickListener = AdapterView.OnItemClickListener { adapterView, view, position, id ->
            val intent = Intent(applicationContext, MapsActivity :: class.java)

            intent.putExtra("address",nameArray[position])
            intent.putExtra("lat",locationArray[position].latitude)
            intent.putExtra("lon",locationArray[position].longitude)
            intent.putExtra("info","old")
            startActivity(intent)


        }

        super.onResume()
    }



    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val manuInflater=menuInflater
        manuInflater.inflate(R.menu.map,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if(item!!.itemId == R.id.map)
        {
            val  intent = Intent(applicationContext,MapsActivity :: class.java)
            intent.putExtra("info","new")
            startActivity(intent)
        }
        return super.onOptionsItemSelected(item)
    }


}

