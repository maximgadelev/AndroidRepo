package com.example.myapplication.model

import androidx.annotation.DrawableRes
import androidx.annotation.RawRes

class Track (
    val id:Int,
    val title:String,
    val author:String,
    @DrawableRes val cover:Int,
    @RawRes val sound:Int
)

