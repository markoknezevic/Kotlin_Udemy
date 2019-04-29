package com.example.travel_book

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import java.util.*

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    var locationManager : LocationManager?= null
    var locationListener : LocationListener ?= null


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

        locationListener = object  : LocationListener{
            override fun onLocationChanged(location: Location?) {

                if(location != null){
                    try {
                        mMap.clear()
                        val userLocation = LatLng(location.latitude, location.longitude)
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(userLocation,13f))
                    }catch (e : Exception){
                        e.printStackTrace()
                    }


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
            ActivityCompat.requestPermissions(this,arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),1)

        }else{
           // locationManager!!.requestLocationUpdates(LocationManager.GPS_PROVIDER,2,2f,locationListener)
            val intent = intent
            val info =intent.getStringExtra("info")

            if(info.equals("new")) {
                val location = locationManager!!.getLastKnownLocation(LocationManager.GPS_PROVIDER)
                val uloc = LatLng(location.latitude, location.longitude)
                mMap.addMarker(MarkerOptions().position(uloc).title("Your Location"))
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(uloc, 13f))

            }else{
                val address = intent.getStringExtra("address")
                val lat = intent.getDoubleExtra("lat",0.0)
                val lon = intent.getDoubleExtra("lon",0.0)

                val location = LatLng(lat,lon)

                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location,13f))
                mMap.addMarker(MarkerOptions().position(location).title(address))

            }
        }


    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if(grantResults.size > 0 && requestCode ==1){
            if(ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
               locationManager!!.requestLocationUpdates(LocationManager.GPS_PROVIDER, 2, 2f, locationListener)

            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }


    var myListener = object : GoogleMap.OnMapLongClickListener {
        override fun onMapLongClick(p0: LatLng?) {
            mMap.clear()
            val geocoder = Geocoder(applicationContext, Locale.getDefault())
            var address = ""
            val addresslist=geocoder.getFromLocation(p0!!.latitude,p0.longitude,1)
            if(addresslist.size > 0){

                if(addresslist[0].thoroughfare != null)
                    address += addresslist[0].thoroughfare
                if(addresslist[0].subThoroughfare != null)
                    address += addresslist[0].subThoroughfare

            }else{
                address = "New Place"
            }
            mMap.addMarker(MarkerOptions().position(p0).title(address))
            Toast.makeText(applicationContext,"New Place Created",Toast.LENGTH_LONG).show()
            try {
                val latitude = p0.latitude.toString()
                val longitude = p0.longitude.toString()
                val database = openOrCreateDatabase("Places",Context.MODE_PRIVATE,null)

                database.execSQL("CREATE TABLE IF NOT EXISTS places(name VARCHAR,latitude VARCHAR,longitude VARCHAR)")
                val sqlString="INSERT INTO places(name,latitude,longitude) VALUES(?,?,?)"
                val statment = database.compileStatement(sqlString)
                statment.bindString(1,address)
                statment.bindString(2,latitude)
                statment.bindString(3,longitude)

                statment.execute()

            }catch (e : Exception){
                e.printStackTrace()
            }
        }

    }
}
