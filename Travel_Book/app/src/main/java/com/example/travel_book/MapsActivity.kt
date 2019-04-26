package com.example.travel_book

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.LocationManager
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import java.util.*

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    var locationManager : LocationManager ?= null
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
        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager


        if(ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){

            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),1)

        }else{
            var geocoder = Geocoder(applicationContext, Locale.getDefault())

            var address=""
            try {


                val location = locationManager!!.getLastKnownLocation(LocationManager.GPS_PROVIDER)
                val userLocation = LatLng(location.latitude, location.longitude)
                val addreslist = geocoder.getFromLocation(userLocation.latitude,userLocation.longitude,1)

                if(addreslist.size > 0){

                    if(addreslist[0].thoroughfare != null){
                        address += addreslist[0].thoroughfare.toString()+" "
                    }
                    if(addreslist[0].subThoroughfare != null){
                        address += addreslist[0].subThoroughfare
                    }

                }
                if(address.equals(""))
                    address = "No Adress"

                mMap.addMarker(MarkerOptions().position(userLocation).title(address))
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(userLocation,15f))

            }catch (e : Exception){

            }
        }

    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if(requestCode == 1 && grantResults.size > 0){
            if(ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
                try {

                    val location = locationManager!!.getLastKnownLocation(LocationManager.GPS_PROVIDER)
                    val userLocation = LatLng(location.latitude, location.longitude)
                    var geocoder = Geocoder(applicationContext, Locale.getDefault())

                    val addreslist = geocoder.getFromLocation(userLocation.latitude,userLocation.longitude,1)

                    var address=""

                    if(addreslist.size > 0){

                        if(addreslist[0].thoroughfare != null){
                            address += addreslist[0].thoroughfare.toString()+" "
                        }
                        if(addreslist[0].subThoroughfare != null){
                            address += addreslist[0].subThoroughfare
                        }

                    }

                    if(address.equals(""))
                        address = "No Adress"

                    mMap.addMarker(MarkerOptions().position(userLocation).title(address))
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(userLocation, 15f))

                }catch (e : java.lang.Exception){

                }
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }
}
