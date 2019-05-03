package com.example.foursquare_clone

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.support.v7.app.AppCompatActivity
import android.view.View
import kotlinx.android.synthetic.main.activity_place_name_.*

    var globalName = ""
    var globalType = ""
    var globalAtm = ""
    var globalImage : Bitmap? = null
class PlaceName_Activity : AppCompatActivity() {

    var chosenImage : Bitmap? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_place_name_)
    }

    fun selectImage(view : View){
        if(checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            requestPermissions(arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),1)
        }else{
            val intent = Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(intent,2)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if(grantResults.size > 0 && requestCode == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            val intent = Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(intent,2)

        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(requestCode == 2 && resultCode == Activity.RESULT_OK && data != null){
            val image = data.data
            try{
                chosenImage = MediaStore.Images.Media.getBitmap(this.contentResolver,image)
                imageView.setImageBitmap(chosenImage)
            }catch (e : Exception){
                e.printStackTrace()
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    fun next(view : View){
        globalImage = chosenImage
        globalName = nameText.text.toString()
        globalType = typeText.text.toString()
        globalAtm = atmText.text.toString()

        val intent = Intent(applicationContext,MapsActivity :: class.java)
        startActivity(intent)
    }
}
