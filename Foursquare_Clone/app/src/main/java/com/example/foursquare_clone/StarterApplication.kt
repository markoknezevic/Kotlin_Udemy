package com.example.foursquare_clone

import android.app.Application
import com.parse.Parse
import com.parse.ParseACL
import com.parse.ParseUser

class StarterApplication : Application(){

    override fun onCreate() {
        super.onCreate()

        Parse.enableLocalDatastore(this)

        Parse.initialize(this)

        ParseUser.enableAutomaticUser()
        val defaultACL = ParseACL()


        defaultACL.publicReadAccess = true
        defaultACL.publicWriteAccess = true
        ParseACL.setDefaultACL(defaultACL,true)
    }

}