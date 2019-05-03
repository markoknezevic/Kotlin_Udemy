package com.example.currency_converter

import android.os.AsyncTask
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }

    fun get(view : View){
        val downloadData = Download()

        try {

            val url = "http://data.fixer.io/api/latest?access_key=04cc7143565e2be1fb96a9d8c1f28f7f&format=1"
            val chosenBase = editText.text.toString()
            //da se link zavrsava base=USD samo stavimo url+chosenBase
            downloadData.execute(url)

        }catch (e : Exception){
            e.printStackTrace()
        }
    }

    inner class Download : AsyncTask<String,Void,String>(){
        override fun doInBackground(vararg p0: String?): String {// vararg niz stringova
            var result = ""
            var url : URL
            var httpURLConnection : HttpURLConnection

            try {
                url=URL(p0[0])
                httpURLConnection = url.openConnection() as HttpURLConnection
                val inputStream = httpURLConnection.inputStream
                val inputStreamReader = InputStreamReader(inputStream)

                var data = inputStreamReader.read()

                while (data > 0){
                    var character = data.toChar()
                    result += character

                    data = inputStreamReader.read()

                }
                return result

            }catch (e : Exception){
                e.printStackTrace()
                return result
            }
            return result
        }



        override fun onPostExecute(result: String?) {
            try {
                val jSONObject = JSONObject(result)
                val base = jSONObject.getString("base")
                val rates = jSONObject.getString("rates")

                val newJSONObject = JSONObject(rates)
                val chf = newJSONObject.getString("CHF")
                val czk = newJSONObject.getString("CZK")
                val tl = newJSONObject.getString("TRY")

                chfText.text = "CHF: "+chf
                czkText.text = "CZK: "+czk
                tryText.text = "TRY: "+tl
            }catch (e : Exception){
                e.printStackTrace()
            }
        }


    }
}
