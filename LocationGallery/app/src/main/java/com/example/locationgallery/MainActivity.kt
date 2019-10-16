package com.example.locationgallery

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var mAuth : FirebaseAuth ?= null;
    var mAuthListenet : FirebaseAuth.AuthStateListener ?= null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mAuth = FirebaseAuth.getInstance();

        mAuthListenet = FirebaseAuth.AuthStateListener {  }



    }

    fun SingIn(view : View){

        mAuth!!.signInWithEmailAndPassword(emailText.text.toString(),passwordText.text.toString()).addOnCompleteListener { task ->
            if(task.isSuccessful){
                val intent = Intent(applicationContext,Locations::class.java);
                startActivity(intent);

            }
        }.addOnFailureListener { exception ->

            Toast.makeText(applicationContext,exception.localizedMessage,Toast.LENGTH_LONG).show();
        }
    }

    fun SingUp(view : View){
        mAuth!!.createUserWithEmailAndPassword(emailText.text.toString(),passwordText.text.toString()).addOnCompleteListener { task ->
            if(task.isSuccessful){

                Toast.makeText(applicationContext,"User created",Toast.LENGTH_LONG).show()
                val intent = Intent(applicationContext,Locations :: class.java)
                startActivity(intent)

            }
        }.addOnFailureListener { exception ->

            Toast.makeText(applicationContext,exception.localizedMessage,Toast.LENGTH_LONG);

        }
    }

    }

