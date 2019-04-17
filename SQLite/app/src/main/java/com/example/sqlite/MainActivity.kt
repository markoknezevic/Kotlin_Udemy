package com.example.sqlite

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        try {
            val myDatabase = this.openOrCreateDatabase("Music", Context.MODE_PRIVATE, null)
            //myDatabase.execSQL("CREATE TABLE IF NOT EXISTS musicians(name VARCHAR, age INT(2))")
            //myDatabase.execSQL("INSERT INTO musicians(name,age) VALUES ('James',50)")
            //myDatabase.execSQL("INSERT INTO musicians(name,age) VALUES ('Kirk',60)")
            //myDatabase.execSQL("INSERT INTO musicians(name,age) VALUES ('Lars',58)")
            //myDatabase.execSQL("INSERT INTO musicians(name,age) VALUES ('Rob',54)")
            myDatabase.execSQL("UPDATE musicians SET age=30 WHERE name='Lars'")
            myDatabase.execSQL("DELETE FROM musicians WHERE name='Rob'")
            var cursor=myDatabase.rawQuery("SELECT * FROM musicians",null)

            val name=cursor.getColumnIndex("name")
            val age=cursor.getColumnIndex("age")

            cursor.moveToFirst()
            while(cursor!=null){
                textView.text=" Name: "+cursor.getString(name)+" Age: "+cursor.getString(age)
                println(" Name: "+cursor.getString(name)+" Age: "+cursor.getString(age))
                cursor.moveToNext()
            }

            if(cursor!=null){
                cursor!!.close()
            }
        }
        catch (e:Exception){
            e.printStackTrace()
        }
    }
}
