package com.example.foursquare_clone

import android.graphics.BitmapFactory
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.parse.ParseFile
import com.parse.ParseObject
import com.parse.ParseQuery
import kotlinx.android.synthetic.main.activity_detail_.*

class Detail_Activity : AppCompatActivity(), OnMapReadyCallback {


    private lateinit var mMap: GoogleMap
    var chosenPlace =""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_)
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.mapView) as SupportMapFragment
        mapFragment.getMapAsync(this)

        val intent = intent
        chosenPlace = intent.getStringExtra("name")
    }


    override fun onMapReady(p0: GoogleMap?) {
        mMap = p0!!

        val query = ParseQuery<ParseObject>("Location")
        query.whereEqualTo("name",chosenPlace)


        query.findInBackground { objects, e ->
            if(e != null){
                Toast.makeText(applicationContext,e.localizedMessage,Toast.LENGTH_LONG).show()
            }else{
                if(objects.size > 0){

                    for(parseObject in objects){

                        val image = parseObject.get("image") as ParseFile

                        image.getDataInBackground { data, e ->
                            if(e != null){
                                Toast.makeText(applicationContext,e.localizedMessage,Toast.LENGTH_LONG).show()
                            }else{

                                val bitmap = BitmapFactory.decodeByteArray(data,0,data.size)
                                imageView2.setImageBitmap(bitmap)
                                val name = parseObject.get("name") as String
                                val lat = parseObject.get("lat") as String
                                val lon = parseObject.get("lon") as String
                                val type = parseObject.get("type") as String
                                val atm = parseObject.get("atm") as String

                                nameView.setText(name)
                                typeView.setText(type)
                                atmView.setText(atm)

                                val loc = LatLng(lat.toDouble(),lon.toDouble())
                                mMap.addMarker(MarkerOptions().position(loc).title(name))
                                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(loc,13f))


                            }
                        }

                    }
                }
            }
        }
    }
}
