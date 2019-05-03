package com.example.foursquare_clone

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import com.parse.LogInCallback
import com.parse.ParseAnalytics
import com.parse.ParseException
import com.parse.ParseUser
import kotlinx.android.synthetic.main.signin_activity.*

class SignIn_Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.signin_activity)


        ParseAnalytics.trackAppOpenedInBackground(intent)




    }
    fun signIn(view : View){
        ParseUser.logInInBackground(usernameText.text.toString(),passwordText.text.toString(), LogInCallback { user, e ->
            if(e != null){
                Toast.makeText(applicationContext,e.localizedMessage,Toast.LENGTH_LONG).show()
            }else{
                Toast.makeText(applicationContext,"Welcome "+usernameText.text.toString(),Toast.LENGTH_LONG).show()
                val intent = Intent(applicationContext,Location_Activity :: class.java)
                startActivity(intent)
            }
        })
    }

    fun signUp(view : View){
        val user = ParseUser()
        user.username = usernameText.text.toString()
        user.setPassword(passwordText.text.toString())

        user.signUpInBackground { e : ParseException? ->
            if(e != null){
                Toast.makeText(applicationContext,e.localizedMessage,Toast.LENGTH_LONG).show()
            }else{
                Toast.makeText(applicationContext,"User created",Toast.LENGTH_LONG).show()

                val intent = Intent(applicationContext,Location_Activity :: class.java)
                startActivity(intent)
            }
        }
    }




    // dodaj u gradle dependancy
}
