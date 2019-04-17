package com.example.landmarkbook

import android.graphics.Bitmap

class Global{
    companion object Chosen{
        var cimage: Bitmap?=null
        fun returnImage():Bitmap{
            return  cimage!!
        }
    }

}