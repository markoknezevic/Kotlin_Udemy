package com.example.foursquare_clone

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.parse.ParseFile
import com.parse.ParseObject
import com.parse.ParseUser
import java.io.ByteArrayOutputStream

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap

    var locationListener : LocationListener? = null
    var locationManager : LocationManager? = null
    var lat = ""
    var lon = ""

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.save_location,menu)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if(item!!.itemId == R.id.save_location){
            saveToParse()
        }
        return super.onOptionsItemSelected(item)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        mMap.setOnMapLongClickListener(myListener)
        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager

        locationListener = object : LocationListener{

            override fun onLocationChanged(location: Location?) {
                if(location != null){
                    mMap.clear()
                    val userLocation = LatLng(location!!.latitude,location.longitude)
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(userLocation,13f))
                }
            }

            override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {

            }

            override fun onProviderEnabled(provider: String?) {

            }

            override fun onProviderDisabled(provider: String?) {

            }

        }

        if(ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){

            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),2)

        }else{
            //locationManager!!.requestLocationUpdates(LocationManager.GPS_PROVIDER,2,2f,locationListener)
            val loc = locationManager!!.getLastKnownLocation(LocationManager.GPS_PROVIDER)
            val uloc = LatLng(loc.latitude,loc.longitude)
            mMap.addMarker(MarkerOptions().position(uloc).title("Your location"))
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(uloc,12f))
        }

    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if(requestCode == 2 && grantResults.size > 0){
            if(ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
                //locationManager!!.requestLocationUpdates(LocationManager.GPS_PROVIDER,2,2f,locationListener)
                val loc = locationManager!!.getLastKnownLocation(LocationManager.GPS_PROVIDER)
                val uloc = LatLng(loc.latitude,loc.longitude)
                mMap.addMarker(MarkerOptions().position(uloc).title("Your location"))
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(uloc,12f))
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }
    val myListener = object : GoogleMap.OnMapLongClickListener {
        override fun onMapLongClick(p0: LatLng) {
            mMap.clear()

            mMap.addMarker(MarkerOptions().position(p0).title(globalName))
            lat = p0.latitude.toString()
            lon = p0.longitude.toString()
            Toast.makeText(applicationContext,"Now save this location",Toast.LENGTH_LONG).show()
        }

    }

    fun saveToParse(){
        val byteArrayOutputStream = ByteArrayOutputStream()
        globalImage!!.compress(Bitmap.CompressFormat.PNG,50,byteArrayOutputStream)
        val bytes = byteArrayOutputStream.toByteArray()

        val parseFile = ParseFile("image.png",bytes)

        val parseObj = ParseObject("Location")
        parseObj.put("name", globalName)
        parseObj.put("type", globalType)
        parseObj.put("atm", globalAtm)
        parseObj.put("lat", lat)
        parseObj.put("lon", lon)
        parseObj.put("username",ParseUser.getCurrentUser().username.toString())
        parseObj.put("image",parseFile)
        parseObj.saveInBackground {e ->
            if(e != null){
                Toast.makeText(applicationContext,e.localizedMessage,Toast.LENGTH_LONG).show()
            }else{
                Toast.makeText(applicationContext,"Location Created",Toast.LENGTH_LONG).show()
                val intent = Intent(applicationContext,Location_Activity :: class.java)
                startActivity(intent)
            }

        }
    }
}
