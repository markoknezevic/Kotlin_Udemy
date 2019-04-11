package com.example.alertdialog

import android.content.DialogInterface
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    fun save(view:View){
        var alert=AlertDialog.Builder(this)

        alert.setTitle("Save")
        alert.setMessage("Are You Shure")
        alert.setPositiveButton("Yes") { dialog: DialogInterface?, i: Int ->
            Toast.makeText(applicationContext, "Saved"+i, Toast.LENGTH_LONG).show()
            textView.text="YES"
        }
        alert.setNegativeButton("No"){dialog: DialogInterface?, i: Int ->
            Toast.makeText(applicationContext,"Not Saved"+i,Toast.LENGTH_LONG).show()
            textView.text="NO"

        }
        alert.show()

    }
}
