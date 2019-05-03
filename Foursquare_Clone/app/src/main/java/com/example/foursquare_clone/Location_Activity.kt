package com.example.foursquare_clone

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.Toast
import com.parse.ParseObject
import com.parse.ParseQuery
import kotlinx.android.synthetic.main.activity_location_.*

class Location_Activity : AppCompatActivity() {
    var nameArray = ArrayList<String>()

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.add_location,menu)


        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if(item?.itemId == R.id.add_location){
            val intent = Intent(applicationContext,PlaceName_Activity :: class.java)
            startActivity(intent)
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_location_)

        getParseData()

        listView.setOnItemClickListener { parent, view, position, id ->
            val intent = Intent(applicationContext,Detail_Activity :: class.java)
            intent.putExtra("name",nameArray[position])
            startActivity(intent)
        }
    }



    fun getParseData(){
        val arrayAdapter = ArrayAdapter(this,android.R.layout.simple_list_item_1,nameArray)
        listView.adapter = arrayAdapter

        val query = ParseQuery.getQuery<ParseObject>("Location")
        query.findInBackground { objects, e ->
            if(e != null){
                Toast.makeText(applicationContext,e.localizedMessage,Toast.LENGTH_LONG).show()
            }else{
                nameArray.clear()
                for(parseObject in objects){
                    val name = parseObject.get("name") as String
                    nameArray.add(name)

                }
                arrayAdapter.notifyDataSetChanged()
            }
        }
    }
}
