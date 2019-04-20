package com.example.artbook

import android.graphics.Bitmap

class Global{
    companion object Image{
        var cimage:Bitmap?=null
        fun getImage():Bitmap{
            return cimage!!
        }
    }
}